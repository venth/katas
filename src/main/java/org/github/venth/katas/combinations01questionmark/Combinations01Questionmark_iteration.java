package org.github.venth.katas.combinations01questionmark;

import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.util.StringUtils;

class Combinations01Questionmark_iteration implements Function<String, Stream<String>> {

    @Override
    public Stream<String> apply(String input) {
        if (!StringUtils.isEmpty(input)) {
            return calculate(input, 0, input.length());
        } else {
            return Stream.empty();
        }
    }

    private Stream<String> calculate(String input, int currentPos, int inputLength) {
        Stream<String> combinations = Stream.of("");
        while (currentPos < inputLength) {
            int questionMarkPosition = input.indexOf('?', currentPos);
            boolean onlyStaticPart = questionMarkPosition < 0;
            if (onlyStaticPart) {
                int finalCurrentPos = currentPos;
                combinations = combinations.map(part -> part + input.substring(finalCurrentPos));
                currentPos = inputLength;
            } else {
                String staticPart = input.substring(currentPos, questionMarkPosition);
                combinations = combinations.flatMap(part -> Stream.of(
                        part + staticPart + "0",
                        part + staticPart + "1"
                ));
                currentPos = questionMarkPosition + 1;
            }
        }

        return combinations;
    }

    @Override
    public String toString() {
        return "iterative combinator";
    }
}
