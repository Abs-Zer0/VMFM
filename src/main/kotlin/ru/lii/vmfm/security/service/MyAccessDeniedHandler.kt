package ru.lii.vmfm.security.service

import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

@Component
public class MyAccessDeniedHandler : AccessDeniedHandler {

    val logger: Logger = LoggerFactory.getLogger(MyAccessDeniedHandler::class.java)

    @Throws(IOException::class, ServletException::class)
    override fun handle(
            httpServletRequest: HttpServletRequest,
            httpServletResponse: HttpServletResponse,
            e: AccessDeniedException
    ) {

        val auth: Authentication? = SecurityContextHolder.getContext().getAuthentication()

        if (auth != null) {
            logger.info(
                    "User '" +
                            auth.getName() +
                            "' attempted to access the protected URL: " +
                            httpServletRequest.getRequestURI())
            logger.info("exception: " + e.localizedMessage)
        }

        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/403")
    }
}
