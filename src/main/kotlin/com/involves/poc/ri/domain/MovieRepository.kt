package com.involves.poc.ri.domain

import org.eclipse.microprofile.config.inject.ConfigProperty
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import java.net.URI
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

@ApplicationScoped
class MovieRepository(
    @ConfigProperty(name = "dynamo.table.movie-table")
    private val movieTableName: String,
    dynamoDBClient: DynamoDbEnhancedClient
) {

    private val tableMovie = dynamoDBClient.table(movieTableName, TableSchema.fromBean(Movie::class.java))

    fun persistMovie(movie: Movie): Unit? {
        return tableMovie.putItem(movie)
    }

    fun findMovie(movieId: String, name: String): Movie? {
        val movieKey = Key.builder().partitionValue(movieId).sortValue(name).build()

        return tableMovie.getItem(movieKey)
    }

}