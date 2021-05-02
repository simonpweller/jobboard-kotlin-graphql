package de.sweller.jobboard

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery

@DgsComponent
class CompanyDataFetcher(
    val companyRepository: CompanyRepository
) {
    @DgsQuery
    fun company(id: String) = companyRepository.findById(id)
}
