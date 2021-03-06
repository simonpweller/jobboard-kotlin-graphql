package de.sweller.jobboard.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import de.sweller.jobboard.config.HEADER_NAME
import de.sweller.jobboard.config.HEADER_PREFIX
import de.sweller.jobboard.config.KEY
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthorizationFilter(authManager: AuthenticationManager) : BasicAuthenticationFilter(authManager) {

    private val algorithm = Algorithm.HMAC256(KEY)
    private val verifier = JWT.require(algorithm).build()

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val token = request.getHeader(HEADER_NAME)?.replace(HEADER_PREFIX, "")
        token?.let {
            try {
                val jwt = verifier.verify(token)
                SecurityContextHolder.getContext().authentication =
                    UsernamePasswordAuthenticationToken(jwt.subject, null, listOf(SimpleGrantedAuthority("ROLE_USER")))
            } catch (ex: JWTVerificationException) {
                SecurityContextHolder.clearContext()
            } catch (ex: AuthenticationException) {
                SecurityContextHolder.clearContext()
            }
        }
        chain.doFilter(request, response)
    }
}

