package com.involves.poc.ri.controller

import com.involves.poc.ri.application.MovieAppService
import com.involves.poc.ri.domain.MovieDTO
import org.apache.http.HttpStatus
import org.apache.http.HttpStatus.SC_CREATED
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class MoviesController {

    @Inject
    @field: Default
    lateinit var movieAppService: MovieAppService

    @POST
    fun createMovie(movie: MovieDTO): Response {
        movieAppService.createMovie(movie)

        return Response.status(SC_CREATED).build()
    }

    @GET
    @Path("/name/{name}/actor/{actor}")
    fun findMovieById(@PathParam("name") name: String, @PathParam("actor") actor: String): Response? {
        val movie = movieAppService.findMovieBy(name, actor)

        return Response.status(HttpStatus.SC_OK).entity(movie).build()
    }

}