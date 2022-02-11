package com.involves.poc.ri.infra.aws

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import javax.ws.rs.Produces

class DynamoDbClientProducer {

    @Produces
    fun dynamoDbClient(): DynamoDbEnhancedClient {
        return DynamoDbEnhancedClient.create()
    }

}