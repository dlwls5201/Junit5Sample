package com.example.junit5sample

import com.google.common.truth.Truth
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource

/**
 * https://junit.org/junit5/docs/current/user-guide/
 * JUnit5 = JUnit Platform + JUnit Jupiter + JUnit Vintage
 *
 * - Test framework on JVM and next version of Junit4
 * - Show the description more detail than Junit4
 */
class Junit5Test {

    @Test
    @DisplayName("you can modify test name freely")
    fun test1() {
        println("test1")
    }

    @ValueSource(
        strings = [
            "1",
            "2",
            "3",
        ]
    )
    @ParameterizedTest(name = "first param is {0}")
    //short, byte, int, long, float, double, char, java.lang.String, java.lang.Class
    fun test2(first: Int) {
        println("first : $first")
    }

    @CsvSource(
        value = [
            "1,2,3",
            "one,two,three"
        ]
    )
    @ParameterizedTest(name = "first param is {0}, second param is {1}, third param is {2}")
    fun test3(first: String, second: String, third: String) {
        println("first : $first, second : $second, third : $third")
    }

    @CsvSource(
        value = [
            "1,2,3",
            "7,3,10",
            "10,20,30"
        ]
    )
    @ParameterizedTest(name = "{0} + {1} = {2}")
    fun test4(firstNumber: Int, secondNumber: Int, expected: Int) {
        val result = Operator.Plus.operation(firstNumber, secondNumber)
        Truth.assertThat(result).isEqualTo(expected)
    }

    @CsvSource(
        delimiter = '=',
        value = [
            "1+2=3", // "1+2" , "3"
            "7+3=10", // "7+3" , "10"
            "10+20=30" // "10+20", "30"
        ]
    )
    @ParameterizedTest(name = "{0} result is {1}")
    fun test5(expression: String, expected: Int) {
        val (first, second) = expression.split(Operator.Plus.sign)
        val actual = Operator.Plus.operation(first.toInt(), second.toInt())
        Truth.assertThat(actual).isEqualTo(expected)
    }

    @EnumSource(Operator::class)
    @ParameterizedTest(name = "show {0} using EnumSource")
    fun test6(rawOperator: Operator) {
        println("show sing : ${rawOperator.sign}")
    }

    @CsvSource(
        value = [
            "Plus", "Minus", "Multiply", "Divide",
        ]
    )
    @ParameterizedTest(name = "{0}")
    fun test7(rawOperator: Operator) {
        println("show sing : ${rawOperator.sign}")
    }

    @CsvSource(
        value = [
            "5,Plus,5,10",
            "10,Minus,4,6",
            "10,Multiply,4,40",
            "12,Divide,4,3",
        ]
    )
    @ParameterizedTest(name = "{0} {1} {2} = {3}")
    fun test8(firstNumber: Int, rawOperator: Operator, secondNumber: Int, expected: Int) {
        val actual = rawOperator.operation(firstNumber, secondNumber)
        Truth.assertThat(actual).isEqualTo(expected)
    }
}