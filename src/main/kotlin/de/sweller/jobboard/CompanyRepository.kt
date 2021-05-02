package de.sweller.jobboard

import org.springframework.data.repository.CrudRepository
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.OneToMany

interface CompanyRepository: CrudRepository<Company, String>

@Entity
class Company(
    @Id var id: String,
    var name: String,
    var description: String,
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    var jobs: MutableSet<Job> = mutableSetOf()
)
