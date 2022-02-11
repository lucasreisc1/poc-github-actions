package com.involves.poc.ri

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager
import org.testcontainers.containers.GenericContainer
import org.testcontainers.utility.DockerImageName

class DynamoDbTestResource: QuarkusTestResourceLifecycleManager {

    private val dynamoDb = GenericContainer<Nothing>(DockerImageName
        .parse("amazon/dynamodb-local:1.11.477")).apply {
        withCommand("-jar DynamoDBLocal.jar -inMemory -sharedDb")
        withExposedPorts(8000, 8000)
    }



    override fun start(): MutableMap<String, String> {
        dynamoDb.start()
        println("############  Starting DynamoDB ${dynamoDb.getMappedPort(8000)}  ############")

        return mutableMapOf(
            "quarkus.dynamodb.endpoint-override" to "http://localhost:${dynamoDb.getMappedPort(8000)}",
            "quarkus.dynamodb.aws.region" to "eu-central-1",
            "dynamo.table.movie-table" to "movie",
            "quarkus.dynamodb.aws.credentials.static-provider.access-key-id" to "test-key",
            "quarkus.dynamodb.aws.credentials.static-provider.secret-access-key" to "test-secret"
        )
    }

    override fun stop() {
        print("Stop DynamoDB")
        dynamoDb.stop()
    }

}


