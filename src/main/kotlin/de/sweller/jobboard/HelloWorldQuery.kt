package de.sweller.jobboard

import com.expediagroup.graphql.server.operations.Query
import org.springframework.stereotype.Component

@Component
class HelloWorldQuery: Query {
    fun greeting() = "Hello GraphQL world!"
}
