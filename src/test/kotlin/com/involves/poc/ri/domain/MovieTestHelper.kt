package com.involves.poc.ri.domain

object MovieTestHelper {

    fun createMovie(
        actor: String = "Tom Holland",
        movieName: String = "Spider-Man: No Way Home",
        actorRole: String = "Peter Parker",
        year: Int = 2020,
        gender: String = "Action"
    ): Movie {
        return Movie(actor, movieName, actorRole, year, gender)
    }

}