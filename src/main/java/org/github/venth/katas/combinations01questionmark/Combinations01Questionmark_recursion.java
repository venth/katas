package org.github.venth.katas.combinations01questionmark;

import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.util.StringUtils;

class Combinations01Questionmark_recursion implements Function<String, Stream<String>> {

    static Stream<String> combinationsOf(String input) {
        return new Combinations01Questionmark_recursion().apply(input);
    }

    @Override
    public Stream<String> apply(String input) {
        if (!StringUtils.isEmpty(input)) {
            return calculate(input, 0, input.length());
        } else {
            return Stream.empty();
        }
    }

    private Stream<String> calculate(String input, int currentPos, int inputLength) {
        if (currentPos >= inputLength) {
            return Stream.of("");
        }

        int questionMarkPosition = input.indexOf('?', currentPos);

        boolean onlyStaticPart = questionMarkPosition < 0;
        if (onlyStaticPart) {
            return Stream.of(input.substring(currentPos));
        }

        String staticPart = input.substring(currentPos, questionMarkPosition);
        return calculate(input, questionMarkPosition + 1, inputLength)
                .flatMap(remainingPart -> Stream.of(staticPart + "0", staticPart + "1").map(part -> part + remainingPart));
    }

    @Override
    public String toString() {
        return "recursive combinator";
    }
}
