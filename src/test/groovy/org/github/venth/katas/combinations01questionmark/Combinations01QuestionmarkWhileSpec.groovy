package org.github.venth.katas.combinations01questionmark

import java.util.stream.Collectors
import java.util.stream.Stream

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static Combinations01Questionmark_iteration.combinationsOf
import static java.util.stream.Collectors.joining

class Combinations01QuestionmarkWhileSpec extends Specification {

    @Unroll
    def "calculates combinations for: #input with #combinator"() {
        when:
            def calculatedCombinations = combinator.apply(input)
                    .sorted()
                    .collect(Collectors.toList())

        then:
            calculatedCombinations == expectetCombinations
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
            iterativeCombinator || ''      || []
            iterativeCombinator || '0'     || ['0']
            iterativeCombinator || '1'     || ['1']
            iterativeCombinator || '?'     || ['0', '1']
            iterativeCombinator || '?0'    || ['00', '10']
            iterativeCombinator || '0?'    || ['00', '01']
            iterativeCombinator || '0?0'   || ['000', '010']
            iterativeCombinator || '0?0?0' || ['00000', '00010', '01000', '01010']
            iterativeCombinator || '??'    || ['00', '01', '10', '11']
    }

    @Unroll
    def '#combinator calculates big number of question marks'() {
        given:
            def bigNumberOfQuestionMarks = 40000
            def aLotOfSolitudeQuestionMarks = Stream.generate({
                "?"
            }).limit(bigNumberOfQuestionMarks).collect(joining())
        when:
            def numberOfCalculatedCombinations = combinationsOf(aLotOfSolitudeQuestionMarks).count()
        then:
            bigNumberOfQuestionMarks * 2 == numberOfCalculatedCombinations

        where:
            combinator << [recursiveCombinator, iterativeCombinator]
    }

    @Shared
    def recursiveCombinator = new Combinations01Questionmark_recursion()

    @Shared
    def iterativeCombinator = new Combinations01Questionmark_iteration()
}
