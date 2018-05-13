package org.github.venth.katas.combinations01questionmark

import java.util.stream.Collectors
import java.util.stream.Stream

import spock.lang.Specification
import spock.lang.Unroll

import static java.util.stream.Collectors.joining
import static org.github.venth.katas.combinations01questionmark.Combinations01Questionmark.combinationsOf

class Combinations01QuestionmarkTest extends Specification {

    @Unroll
    def "calculates combinations for: #input"() {
        when:
            def calculatedCombinations = combinationsOf(input).collect(Collectors.toList())

        then:
            calculatedCombinations == expectetCombinations
        where:
            input   || expectetCombinations
            ''      || []
            '0'     || ['0']
            '1'     || ['1']
            '?'     || ['0', '1']
            '?0'    || ['00', '10']
            '0?'    || ['00', '01']
            '0?0'   || ['000', '010']
            '0?0?0' || ['00000', '00010', '01000', '01010']
            '??'    || ['00', '01', '10', '11']
    }

    def 'calculates big number of question marks'() {
        given:
            def bigNumberOfQuestionMarks = 40000
            def aLotOfSolitudeQuestionMarks = Stream.generate({"?"}).limit(bigNumberOfQuestionMarks).collect(joining())
        when:
            def numberOfCalculatedCombinations = combinationsOf(aLotOfSolitudeQuestionMarks).count()
        then:
            bigNumberOfQuestionMarks * 2 == numberOfCalculatedCombinations
    }
}
