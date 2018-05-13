package org.github.venth.katas.combinations01questionmark;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.util.StringUtils;

class Combinations01Questionmark implements Function<String, Stream<String>> {

    static Stream<String> combinationsOf(String input) {
        return new Combinations01Questionmark().apply(input);
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

        List<String> remaining = calculate(input, questionMarkPosition + 1, inputLength).collect(Collectors.toList());
        String staticPart = input.substring(currentPos, questionMarkPosition);

        return Stream.of("0", "1")
                        .map(questionMarkReplacement -> staticPart + questionMarkReplacement)
                        .flatMap(part -> remaining.stream().map(remainingPart -> part + remainingPart));
    }

}
