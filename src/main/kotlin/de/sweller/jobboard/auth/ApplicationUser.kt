package de.sweller.jobboard.auth

import de.sweller.jobboard.Company
import org.springframework.data.repository.CrudRepository
import javax.persistence.*

interface ApplicationUserRepository: CrudRepository<ApplicationUser, Long> {
    fun findByEmail(email: String): ApplicationUser?
}

@Entity
class ApplicationUser (
    @Id var id: String,
    var email: String,
    var password: String,
    @ManyToOne(fetch = FetchType.LAZY)
    var company: Company,
)
