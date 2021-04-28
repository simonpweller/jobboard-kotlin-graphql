package de.sweller.jobboard

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DatabaseSeeding {

    @Bean
    fun seedJobs(
        jobRepository: JobRepository
    ) = ApplicationRunner {
        jobRepository.saveAll(listOf(
            JobEntity(
                id = "rJKAbDd_z",
                title = "Frontend Developer",
                description = "We are looking for a Frontend Developer familiar with React."
            ),
            JobEntity(
                id = "SJRAZDu_z",
                title = "Backend Developer",
                description = "We are looking for a Backend Developer familiar with Node.js and Express."
            ),
            JobEntity(
                id = "rkz1GwOOM",
                title = "Full-Stack Developer",
                description = "We are looking for a Full-Stack Developer familiar with Node.js, Express, and React."
            ),
        ))
    }
}
