package de.sweller.jobboard

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery

@DgsComponent
class JobsDataFetcher(
    val jobRepository: JobRepository,
) {
    @DgsQuery
    fun jobs(): Iterable<Job> = jobRepository.findAll()
}
