package ru.lii.vmfm.security.jwt

import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
public class AuthEntryPointJwt : AuthenticationEntryPoint {

    val logger: Logger = LoggerFactory.getLogger(AuthEntryPointJwt::class.java.name)

    @Throws(IOException::class, ServletException::class)
    override fun commence(
            request: HttpServletRequest,
            response: HttpServletResponse,
            authException: AuthenticationException
    ) {
        logger.error("Unauthorized error: {}", authException.localizedMessage)
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized")
    }
}
