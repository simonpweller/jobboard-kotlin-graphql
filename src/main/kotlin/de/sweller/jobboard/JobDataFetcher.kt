package de.sweller.jobboard

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery

@DgsComponent
class JobDataFetcher(
    val jobRepository: JobRepository,
) {
    @DgsQuery
    fun job(id: String) = jobRepository.findById(id)

    @DgsQuery
    fun jobs(): Iterable<Job> = jobRepository.findAll()
}
