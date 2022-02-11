package com.involves.poc.ri.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.Year

class MovieTest {

    @Test
    fun isCurrentYearMovie() {
        // setup:
        val spiderManNoWayHome = MovieTestHelper.createMovie()
        // execute:
        val currentYearMovie = spiderManNoWayHome.isCurrentYearMovie()
        // verify:
        Assertions.assertThat(currentYearMovie).isFalse()
    }

    @Test
    fun isCurrentYearMovie_currentYearMovie() {
        // setup:
        val interestelar = MovieTestHelper.createMovie("Matthew McConaughey", movieName = "Interestelar", year = Year.now().value)
        // execute:
        val currentYearMovie = interestelar.isCurrentYearMovie()
        // verify:
        Assertions.assertThat(currentYearMovie).isTrue()
    }

}