package org.github.venth.katas.combinations01questionmark;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class Combinations01Questionmark_stream implements Function<String, Stream<String>> {

    @Override
    public Stream<String> apply(String input) {
        return Stream.of(input)
                .filter(i -> i.length() > 0)
                .flatMap(QuestionMarkIterator::iterate)
                .reduce(Stream.of(""), (reduced, part) -> reduced.flatMap(begin -> part.combinations().map(rest -> begin + rest)), Stream::concat)
                .filter(combination -> combination.length() > 0);
    }

    @Override
    public String toString() {
        return "streaming combinator";
    }

    static class QuestionMarkIterator implements Iterator<Part> {

        private final String input;

        private Part part;

        private final int inputLength;

        QuestionMarkIterator(String input) {
            this.part = new StaticPart(input, -1, 0);
            this.input = input;
            this.inputLength = input.length();
        }

        static Stream<Part> iterate(String input) {
            return StreamSupport.stream(
                    Spliterators.spliteratorUnknownSize(new QuestionMarkIterator(input), Spliterator.ORDERED),
                    false);
        }

        @Override
        public boolean hasNext() {
            return part.endPos() < inputLength;
        }

        @Override
        public Part next() {
            int questionMarkPos = input.indexOf('?', part.endPos());
            Part newPart;
            if (questionMarkPos < 0) {
                newPart = new StaticPart(input, part.endPos(), inputLength);
            } else {
                newPart = new CombinedPart(input, part.endPos(), questionMarkPos + 1);
            }

            part = newPart;
            return part;
        }
    }

    interface Part {

        int endPos();

        Stream<String> combinations();
    }

    static class CombinedPart implements Part {

        private final String input;

        final int startPos;

        final int endPos;

        CombinedPart(String input, int startPos, int endPos) {
            this.input = input;
            this.startPos = startPos;
            this.endPos = endPos;
        }

        @Override
        public String toString() {
            return "Part[" + input.substring(startPos, endPos) + "]";
        }

        @Override
        public Stream<String> combinations() {
            String part = input.substring(startPos, endPos);
            return Stream.of(part.replace('?', '0'), part.replace('?', '1'));
        }

        @Override
        public int endPos() {
            return endPos;
        }
    }

    static class StaticPart implements Part {

        private final String input;

        final int startPos;

        final int endPos;

        StaticPart(String input, int startPos, int endPos) {
            this.input = input;
            this.startPos = startPos;
            this.endPos = endPos;
        }

        @Override
        public String toString() {
            return "Part[" + input.substring(startPos, endPos) + "]";
        }

        @Override
        public Stream<String> combinations() {
            String part = input.substring(startPos, endPos);
            return Stream.of(part);
        }

        @Override
        public int endPos() {
            return endPos;
        }
    }
}
