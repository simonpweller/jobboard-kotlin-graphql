package de.sweller.jobboard.config

import de.sweller.jobboard.auth.ApplicationUserDetailsService
import de.sweller.jobboard.auth.AuthenticationFilter
import de.sweller.jobboard.auth.AuthorizationFilter
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder

const val SIGN_UP_URL = "/signup"
const val HEADER_NAME = "Authorization"
const val HEADER_PREFIX = "Bearer "
const val EXPIRATION_TIME = 1000L * 60 * 30
val KEY = System.getenv()["KEY"]

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
class SecurityConfig(
    val applicationUserDetailsService: ApplicationUserDetailsService,
    val passwordEncoder: PasswordEncoder
): WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http
            .cors().and()
            .csrf().disable()
            .addFilter(AuthenticationFilter(authenticationManager()))
            .addFilter(AuthorizationFilter(authenticationManager()))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .userDetailsService(applicationUserDetailsService)
            .passwordEncoder(passwordEncoder)
    }
}
