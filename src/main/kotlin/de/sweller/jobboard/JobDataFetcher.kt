package de.sweller.jobboard

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.DgsQuery
import de.sweller.jobboard.auth.ApplicationUserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.server.ResponseStatusException

@DgsComponent
class JobDataFetcher(
    val jobRepository: JobRepository,
    val applicationUserRepository: ApplicationUserRepository
) {
    @DgsQuery
    fun job(id: String) = jobRepository.findById(id)

    @DgsQuery
    fun jobs(): Iterable<Job> = jobRepository.findAll()

    @DgsMutation
    @Secured("ROLE_USER")
    fun createJob(input: CreateJobInput): Job {
        val user = applicationUserRepository
            .findByEmail(SecurityContextHolder.getContext().authentication.principal.toString())
            ?: throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        return jobRepository.save(
            Job(
                generateId(),
                input.title,
                user.company,
                input.description,
            )
        )
    }

    class CreateJobInput(
        val title: String,
        val description: String
    )
}
