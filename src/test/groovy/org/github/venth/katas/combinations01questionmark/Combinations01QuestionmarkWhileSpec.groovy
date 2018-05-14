package org.github.venth.katas.combinations01questionmark

import java.util.stream.Collectors
import java.util.stream.Stream

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static java.util.stream.Collectors.joining

class Combinations01QuestionmarkWhileSpec extends Specification {

    @Unroll
    def "calculates combinations for: #input with #combinator"() {
        when:
            def calculatedCombinations = combinator.apply(input)
                    .sorted()
                    .collect(Collectors.toList())

        then:
            calculatedCombinations == expectetCombinations.sort()
        where:
            combinator          || input   || expectetCombinations
            recursiveCombinator || ''      || []
            recursiveCombinator || '0'     || ['0']
            recursiveCombinator || '1'     || ['1']
            recursiveCombinator || '?'     || ['0', '1']
            recursiveCombinator || '?0'    || ['00', '10']
            recursiveCombinator || '0?'    || ['00', '01']
            recursiveCombinator || '0?0'   || ['000', '010']
            recursiveCombinator || '0?0?0' || ['00000', '00010', '01000', '01010']
            recursiveCombinator || '??'    || ['00', '01', '10', '11']
            recursiveCombinator || '???'    || ['000', '100', '010', '001', '110', '101', '011', '111']
            iterativeCombinator || ''      || []
            iterativeCombinator || '0'     || ['0']
            iterativeCombinator || '1'     || ['1']
            iterativeCombinator || '?'     || ['0', '1']
            iterativeCombinator || '?0'    || ['00', '10']
            iterativeCombinator || '0?'    || ['00', '01']
            iterativeCombinator || '0?0'   || ['000', '010']
            iterativeCombinator || '0?0?0' || ['00000', '00010', '01000', '01010']
            iterativeCombinator || '???'    || ['000', '100', '010', '001', '110', '101', '011', '111']
            iterativeCombinator || '??'    || ['00', '01', '10', '11']
            streamingCombinator || ''      || []
            streamingCombinator || '?0'    || ['00', '10']
            streamingCombinator || '0'     || ['0']
            streamingCombinator || '1'     || ['1']
            streamingCombinator || '?'     || ['0', '1']
            streamingCombinator || '0?'    || ['00', '01']
            streamingCombinator || '0?0'   || ['000', '010']
            streamingCombinator || '0?0?0' || ['00000', '00010', '01000', '01010']
            streamingCombinator || '??'    || ['00', '01', '10', '11']
            streamingCombinator || '???'    || ['000', '100', '010', '001', '110', '101', '011', '111']
    }

    @Unroll
    def '#combinator calculates big number of question marks'() {
        given:
            def bigNumberOfQuestionMarks = 25
            def aLotOfSolitudeQuestionMarks = Stream.generate({
                "?"
            }).limit(bigNumberOfQuestionMarks).collect(joining())
        when:
            def numberOfCalculatedCombinations = combinator.apply(aLotOfSolitudeQuestionMarks).count()
        then:
            2.power(bigNumberOfQuestionMarks) == numberOfCalculatedCombinations

        where:
            combinator << [streamingCombinator, recursiveCombinator, iterativeCombinator]
    }

    @Shared
    def recursiveCombinator = new Combinations01Questionmark_recursion()

    @Shared
    def iterativeCombinator = new Combinations01Questionmark_iteration()

    @Shared
    def streamingCombinator = new Combinations01Questionmark_stream()
}
