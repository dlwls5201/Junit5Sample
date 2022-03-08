package com.example.junit5sample

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class Junit5Test {

    @BeforeEach
    fun setup() {
        println("setup")
    }

    @Test
    @DisplayName("junit5 test1 sample")
    fun test1() {
        println("test1")
    }
}