package com.involves.poc.ri.domain

import org.eclipse.microprofile.config.inject.ConfigProperty
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import javax.enterprise.context.ApplicationScoped

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

    fun findMovie(name: String, actor: String): Movie? {
        val movieKey = Key.builder().partitionValue(actor).sortValue(name).build()

        return tableMovie.getItem(movieKey)
    }

}