package ru.lii.vmfm.security.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import ru.lii.vmfm.security.jwt.AuthEntryPointJwt
import ru.lii.vmfm.security.jwt.AuthTokenFilter
import ru.lii.vmfm.security.service.UserDetailsServiceImpl

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig() : WebSecurityConfigurerAdapter() {
    @Autowired lateinit var userDetailsService: UserDetailsServiceImpl

    @Autowired lateinit var unauthorizedHandler: AuthEntryPointJwt

    @Bean
    fun authenticationJwtTokenFilter(): AuthTokenFilter {
        return AuthTokenFilter()
    }

    @Throws(Exception::class)
    override fun configure(authenticationManagerBuilder: AuthenticationManagerBuilder) {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
    }

    @Throws(Exception::class)
    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/auth/**")
                .permitAll()
                .antMatchers("/api/test/**")
                .permitAll()
                .anyRequest()
                .authenticated()

        http.addFilterBefore(
                authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }
}