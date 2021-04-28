package de.sweller.jobboard

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Query
import org.springframework.stereotype.Component

@Component
class JobQuery(
    val jobRepository: JobRepository
): Query {
    fun jobs() = jobRepository.findAll().map { Job(ID(it.id), it.title, it.description) }
}

data class Job(
    val id: ID,
    val title: String,
    val description: String,
)
