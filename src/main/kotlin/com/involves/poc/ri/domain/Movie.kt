package com.involves.poc.ri.domain

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondarySortKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey
import java.time.Year

@DynamoDbBean
class Movie(
    @get:DynamoDbPartitionKey
    var actor: String? = null,
    @get:DynamoDbSortKey
    var name: String? = null,
    @get:DynamoDbSecondarySortKey(indexNames = ["index_actorId_roleId"])
    var actorRole: String? = null,
    @get:DynamoDbSecondarySortKey(indexNames = ["index_actorId_yearId"])
    var year: Int? = null,
    @get:DynamoDbSecondarySortKey(indexNames = ["index_actorId_genderId"])
    var gender: String? = null
) {

    companion object {
        fun mutate(dto: MovieDTO): Movie {
            return Movie(dto.actor, dto.name, dto.actorRole, dto.year, dto.gender)
        }
    }


    fun isCurrentYearMovie(): Boolean {
        return year == Year.now().value
    }

    fun mutate(): MovieDTO {
        return MovieDTO(actor!!, name!!, actorRole!!, year!!, gender!!)
    }

}