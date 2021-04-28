package de.sweller.jobboard

import org.springframework.data.repository.CrudRepository
import javax.persistence.Entity
import javax.persistence.Id

interface JobRepository: CrudRepository<JobEntity, String> {}

@Entity
data class JobEntity(
    @Id var id: String,
    var title: String,
    var description: String,
)
