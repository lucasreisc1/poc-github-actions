package com.involves.poc.ri.application

import com.involves.poc.ri.DynamoDbTestResource
import com.involves.poc.ri.domain.MovieRepository
import com.involves.poc.ri.domain.MovieTestHelper.createMovieDto
import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.junit.QuarkusTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject


@QuarkusTestResource(DynamoDbTestResource::class)
@QuarkusTest
class MovieAppServiceTest {

    @Inject
    lateinit var movieAppService: MovieAppService
    @Inject
    lateinit var movieRepository: MovieRepository


    companion object {
        var existingTable = false
    }

    // TODO mudar para @BeforeAll
    @BeforeEach
    fun beforeAll() {
        if(!existingTable) {
            movieRepository.createTable()
            existingTable = true
        }
    }


    @Test
    fun createMovie() {
        // setup:
        val actorName = "Bob"
        val movieName = "Club"
        val movieDto = createMovieDto(actor = actorName, movieName = movieName)
        // execute:
        movieAppService.createMovie(movieDto)
        val movie = movieAppService.findMovieBy(movieName, actorName)
        // verify:
        assertThat(movie).isNotNull
        assertThat(movie?.actor).isEqualTo(actorName)
        assertThat(movie?.name).isEqualTo(movieName)
    }

    @Test
    fun findMovieBy_whenMovieDonsentExist() {
        // setup:
        val nonExistentKey = "NonExistent"
        val nonExistentSortKey = "NoExistent"
        // execute:
        val movie = movieAppService.findMovieBy(nonExistentKey, nonExistentSortKey)
        // verify:
        assertThat(movie).isNull()
    }
}