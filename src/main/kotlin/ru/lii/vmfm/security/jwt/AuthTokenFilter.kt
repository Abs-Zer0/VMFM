package ru.lii.vmfm.security.jwt

import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import ru.lii.vmfm.security.service.UserDetailsServiceImpl

public class AuthTokenFilter() : OncePerRequestFilter() {
    @Autowired lateinit var jwtUtils: JwtUtils

    @Autowired lateinit var userDetailsService: UserDetailsServiceImpl

    val logger: Logger = LoggerFactory.getLogger(AuthTokenFilter::class.java.name)

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            filterChain: FilterChain
    ) {
        try {
            val jwt: String? = parseJwt(request)
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                val username: String = jwtUtils.getUserNameFromJwtToken(jwt)

                val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)
                val authentication: UsernamePasswordAuthenticationToken =
                        UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities())
                authentication.setDetails(WebAuthenticationDetailsSource().buildDetails(request))

                SecurityContextHolder.getContext().setAuthentication(authentication)
            }
        } catch (e: Exception) {
            logger.error("Cannot set user authentication: {}", e)
        }

        filterChain.doFilter(request, response)
    }

    fun parseJwt(request: HttpServletRequest): String? {
        val headerAuth: String = request.getHeader("Authorization")

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length)
        }

        return null
    }
}
