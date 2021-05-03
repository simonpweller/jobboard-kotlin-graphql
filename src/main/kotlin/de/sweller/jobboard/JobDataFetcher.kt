package de.sweller.jobboard

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.DgsQuery
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import org.springframework.web.server.ResponseStatusException

@DgsComponent
class JobDataFetcher(
    val jobRepository: JobRepository,
    val companyRepository: CompanyRepository,
) {
    @DgsQuery
    fun job(id: String) = jobRepository.findById(id)

    @DgsQuery
    fun jobs(): Iterable<Job> = jobRepository.findAll()

    @DgsMutation
    @Secured("ROLE_USER")
    fun createJob(input: CreateJobInput): Job {
        val company = companyRepository.findByIdOrNull(input.companyId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return jobRepository.save(
            Job(
                generateId(),
                input.title,
                company,
                input.description,
            )
        )
    }

    class CreateJobInput(
        val companyId: String,
        val title: String,
        val description: String
    )
}
