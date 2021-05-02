package de.sweller.jobboard.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import de.sweller.jobboard.config.EXPIRATION_TIME
import de.sweller.jobboard.config.KEY
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class AuthenticationFilter(private val authManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(
        req: HttpServletRequest,
        res: HttpServletResponse
    ): Authentication {
        val credentials = ObjectMapper().readValue(req.inputStream, ApplicationUser::class.java)
        val authentication = UsernamePasswordAuthenticationToken(credentials.email, credentials.password)
        return authManager.authenticate(authentication)
    }

    override fun successfulAuthentication(
        req: HttpServletRequest,
        res: HttpServletResponse,
        chain: FilterChain,
        auth: Authentication
    ) {
        val user = auth.principal as User
        val exp = Date(System.currentTimeMillis() + EXPIRATION_TIME)
        val algorithm = Algorithm.HMAC256(KEY)
        val token = JWT.create().withSubject(user.username).withExpiresAt(exp).sign(algorithm)
        res.writer.write("""{"token": "$token"}""")
        res.writer.flush()
        res.writer.close()
    }
}
