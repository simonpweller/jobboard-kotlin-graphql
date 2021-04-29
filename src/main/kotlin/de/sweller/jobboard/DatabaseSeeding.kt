package de.sweller.jobboard

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DatabaseSeeding {

    @Bean
    fun seedJobs(
        jobRepository: JobRepository,
        companyRepository: CompanyRepository,
    ) = ApplicationRunner {
        val facegle = Company(
            id = "HJRa-DOuG",
            name = "Facegle",
            description = "We are a startup on a mission to disrupt social search engines. Think Facebook meet Google.",
        )
        val goobook = Company(
            id = "SJV0-wdOM",
            name = "Goobook",
            description = "We are a startup on a mission to disrupt search social media. Think Google meet Facebook.",
        )
        companyRepository.saveAll(listOf(facegle, goobook))

        jobRepository.saveAll(listOf(
            Job(
                id = "rJKAbDd_z",
                company = facegle,
                title = "Frontend Developer",
                description = "We are looking for a Frontend Developer familiar with React."
            ),
            Job(
                id = "SJRAZDu_z",
                company = facegle,
                title = "Backend Developer",
                description = "We are looking for a Backend Developer familiar with Node.js and Express."
            ),
            Job(
                id = "rkz1GwOOM",
                company = goobook,
                title = "Full-Stack Developer",
                description = "We are looking for a Full-Stack Developer familiar with Node.js, Express, and React."
            ),
        ))
    }
}
