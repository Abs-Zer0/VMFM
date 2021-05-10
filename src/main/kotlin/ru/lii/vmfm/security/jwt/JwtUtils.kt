package ru.lii.vmfm.security.jwt

import io.jsonwebtoken.*
import java.util.Date
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import ru.lii.vmfm.security.service.UserDetailsImpl

@Component
public class JwtUtils {
    val logger: Logger = LoggerFactory.getLogger(JwtUtils::class.java.name)

    @Value("${vmfm.jwtSecret}") var jwtSecret: String? = null

    @Value("${vmfm.jwtExpirationMs}") var jwtExpirationMs: Int = 86400000

    fun generateJwtToken(authentication: Authentication): String {

        val userPrincipal: UserDetailsImpl = authentication.getPrincipal() as UserDetailsImpl

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(Date())
                .setExpiration(Date((Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact()
    }

    fun getUserNameFromJwtToken(token: String): String {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject()
    }

    fun validateJwtToken(authToken: String): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
            return true
        } catch (e: SignatureException) {
            logger.error("Invalid JWT signature: {}", e.localizedMessage)
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token: {}", e.localizedMessage)
        } catch (e: ExpiredJwtException) {
            logger.error("JWT token is expired: {}", e.localizedMessage)
        } catch (e: UnsupportedJwtException) {
            logger.error("JWT token is unsupported: {}", e.localizedMessage)
        } catch (e: IllegalArgumentException) {
            logger.error("JWT claims string is empty: {}", e.localizedMessage)
        }

        return false
    }
}
