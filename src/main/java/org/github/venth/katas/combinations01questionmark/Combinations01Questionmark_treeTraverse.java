package org.github.venth.katas.combinations01questionmark;

import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.util.StringUtils;

class Combinations01Questionmark_treeTraverse implements Function<String, Stream<String>> {

    @Override
    public Stream<String> apply(String input) {
        return Stream.empty();
    }

    @Override
    public String toString() {
        return "traversing tree combinator";
    }
}
