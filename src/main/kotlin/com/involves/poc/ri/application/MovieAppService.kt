package com.involves.poc.ri.application

import com.involves.poc.ri.domain.Movie
import com.involves.poc.ri.domain.MovieDTO
import com.involves.poc.ri.domain.MovieRepository
import javax.enterprise.context.ApplicationScoped


@ApplicationScoped
class MovieAppService(private val movieRepository: MovieRepository) {

    fun createMovie(dto: MovieDTO): Unit? {
        val movie = Movie.mutate(dto)

        return movieRepository.persistMovie(movie)
    }

    fun findMovieBy(name: String, actor: String): MovieDTO? {
        return movieRepository.findMovie(name, actor)?.mutate()
    }

}