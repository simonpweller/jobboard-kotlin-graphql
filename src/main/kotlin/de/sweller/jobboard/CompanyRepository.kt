package de.sweller.jobboard

import org.springframework.data.repository.CrudRepository
import javax.persistence.Entity
import javax.persistence.Id

interface CompanyRepository: CrudRepository<Company, String>

@Entity
data class Company(
    @Id var id: String,
    var name: String,
    var description: String,
)
