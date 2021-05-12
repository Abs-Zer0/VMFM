package ru.lii.vmfm.security.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.access.AccessDeniedHandler
import ru.lii.vmfm.db.service.UserService

@Configuration
@EnableWebSecurity
public class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired lateinit var accessDeniedHandler: AccessDeniedHandler

    @Autowired lateinit var userDetailsService: UserService

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    @Autowired
    fun configureGlobal(authenticationManagerBuilder: AuthenticationManagerBuilder) {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {

        /*http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/", "/index", "/home").permitAll()*/
                //.antMatchers("/auth/**").permitAll()
                //.antMatchers("/403").permitAll()
                //.antMatchers("/wspace/**").hasRole("USER")
                //.antMatchers("/actuator", "/actuator/**").hasAnyRole("ADMIN")
                //.antMatchers("/h2-console/**").hasAnyRole("ADMIN")
        /*        .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/auth/login").permitAll()
            .and()
                .logout().permitAll()
            .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
        */

        http.csrf().disable()

        http.authorizeRequests().antMatchers("/", "/index", "/home").permitAll()
        http.authorizeRequests().antMatchers("/auth/**").permitAll()
        http.authorizeRequests().antMatchers("/403").permitAll()
        http.authorizeRequests().antMatchers("/wspace/**").authenticated()//.hasAnyRole("USER", "ADMIN")
        http.authorizeRequests().antMatchers("/actuator", "/actuator/**").hasAnyRole("ADMIN")
        http.authorizeRequests().antMatchers("/h2-console/**").hasAnyRole("ADMIN")
        http.authorizeRequests().anyRequest().authenticated()

        http.formLogin().loginPage("/auth/login").permitAll()
        http.logout().permitAll()

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler)
    }

    /*@Throws(Exception::class)
    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {

        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
    }*/
}
