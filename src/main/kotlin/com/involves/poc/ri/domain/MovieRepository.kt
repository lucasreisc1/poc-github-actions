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

@ApplicationScoped
class MovieRepository(
    @ConfigProperty(name = "dynamo.table.movie-table")
    private val movieTableName: String,
    @ConfigProperty(name = "quarkus.dynamodb.aws.region")
    private val region: String,
    @ConfigProperty(name = "quarkus.dynamodb.endpoint-override")
    private val endpoint: String
) {

    fun persistMovie(movie: Movie): Unit? {
        val dynamoClient = dynamoDbEnhancedClient()

        val tableMovie = dynamoClient.table(movieTableName, TableSchema.fromBean(Movie::class.java))

        return tableMovie.putItem(movie)
    }

    fun findMovie(movieId: String, name: String): Movie? {
        val movieKey = Key.builder().partitionValue(movieId).sortValue(name).build()
        val dynamoClient = dynamoDbEnhancedClient()

        val tableMovie = dynamoClient.table(movieTableName, TableSchema.fromBean(Movie::class.java))

        return tableMovie.getItem(movieKey)
    }

    private fun dynamoDbEnhancedClient(): DynamoDbEnhancedClient {
        val client = DynamoDbClient.builder().region(Region.of(region)).endpointOverride(URI.create(endpoint))
            .credentialsProvider(DefaultCredentialsProvider.builder().build())
            .build()

        return DynamoDbEnhancedClient.builder().dynamoDbClient(client).build()
    }

}