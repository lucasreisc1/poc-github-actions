package com.involves.poc.ri.models

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondarySortKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey

@DynamoDbBean
data class Movie(
    @get:DynamoDbPartitionKey var actor: String? = null,
    @get:DynamoDbSortKey var movie_name: String? = null,
    @get:DynamoDbSecondarySortKey(indexNames = ["index_actorId_roleId"]) var actor_role: String? = null,
    @get:DynamoDbSecondarySortKey(indexNames = ["index_actorId_yearId"]) var year: Int? = null,
    @get:DynamoDbSecondarySortKey(indexNames = ["index_actorId_genderId"]) var gender: String? = null
)