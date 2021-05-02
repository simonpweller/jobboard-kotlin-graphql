package de.sweller.jobboard

import org.springframework.data.repository.CrudRepository
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.ManyToOne

interface JobRepository: CrudRepository<Job, String>

@Entity
class Job(
    @Id var id: String,
    var title: String,
    @ManyToOne(fetch = FetchType.LAZY)
    var company: Company,
    var description: String,
)
