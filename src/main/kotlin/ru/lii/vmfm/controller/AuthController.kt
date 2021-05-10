package ru.lii.vmfm.controller

import java.util.Optional
import javax.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ru.lii.vmfm.db.model.*
import ru.lii.vmfm.db.repository.*
import ru.lii.vmfm.http.request.*
import ru.lii.vmfm.http.response.*
import ru.lii.vmfm.security.jwt.JwtUtils

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired lateinit var authenticationManager: AuthenticationManager

    @Autowired lateinit var userRepository: UserRepository

    @Autowired lateinit var roleRepository: RoleRepository

    @Autowired lateinit var encoder: PasswordEncoder

    @Autowired lateinit var jwtUtils: JwtUtils

    @GetMapping("reg")
    fun getReg(): String {
        return "reg"
    }

    @PostMapping("reg")
    fun postReg(@Valid @RequestBody body: RegRequest): ResponseEntity<*> {
        if (userRepository.existsByUsername(body.username)) {
            return ResponseEntity.badRequest()
                    .body(ErrorResponse("Пользователь с именем '$body.username' уже существует"))
        }

        if (userRepository.existsByEmail(body.email)) {
            return ResponseEntity.badRequest()
                    .body(ErrorResponse("Пользователь с эл.почтой '$body.email' уже существует"))
        }

        val user: User = User(body.username, body.email, encoder.encode(body.password))

        val role: Optional<Role> = roleRepository.findByName("USER")
        if (role.isEmpty) {
            return ResponseEntity.badRequest().body(ErrorResponse("Возникли неполадки на сервере"))
        }

        user.roles = listOf(role.get())
        userRepository.saveAndFlush(user)

        return ResponseEntity.ok(RegResponse(generateJwt(user)))
    }

    @GetMapping("/login")
    fun getLogin():String{
        return "login"
    }

    @PostMapping("/login")
    fun postLogin(@Valid @RequestBody body: LoginResponse):ResponseEntity<*>{

    }

    @Throws(Exception::class)
    private fun validFields(user:User){
        
    }

    private fun generateJwt(user: User): String {
        val authentication: Authentication =
                authenticationManager.authenticate(
                        UsernamePasswordAuthenticationToken(user.username, user.password))

        SecurityContextHolder.getContext().setAuthentication(authentication)
        return jwtUtils.generateJwtToken(authentication)
    }
}
