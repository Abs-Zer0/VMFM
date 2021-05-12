package ru.lii.vmfm.controller

import javax.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import ru.lii.vmfm.db.model.*
import ru.lii.vmfm.db.repository.*
import ru.lii.vmfm.db.service.RoleService
import ru.lii.vmfm.db.service.UserService
import ru.lii.vmfm.http.request.*
import ru.lii.vmfm.http.response.*

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired lateinit var users: UserService

    @Autowired lateinit var roles: RoleService

    @Autowired lateinit var encoder: PasswordEncoder

    @GetMapping("reg")
    fun getReg(model: Model): String {
        model.addAttribute("user", RegRequest("", "", "", ""))

        return "reg"
    }

    @PostMapping("reg")
    fun postReg(@Valid /*@RequestBody*/ body: RegRequest, model: Model): String {
        if (body.password != body.confirmPassword) {
            model.addAttribute("error", "Пароли должны совпадать")
            model.addAttribute("user", body)

            return "reg"
        }

        if (users.existsByUsername(body.username)) {
            model.addAttribute("error", "Пользователь с именем '$body.username' уже существует")
            model.addAttribute("user", body)

            return "reg"
        }

        if (users.existsByEmail(body.email)) {
            model.addAttribute("error", "Пользователь с эл.почтой '$body.email' уже существует")
            model.addAttribute("user", body)

            return "reg"
        }

        val user: User = User(body.username, body.email, encoder.encode(body.password))

        try {
            val role: Role = roles.getUser()
            user.roles = listOf(role)
        } catch (e: Exception) {
            model.addAttribute("error", e.localizedMessage)
            model.addAttribute("user", body)

            return "reg"
        }

        users.addOrUpdateUser(user)

        return "redirect:/"
    }

    @GetMapping("/login")
    fun getLogin(): String {
        return "login"
    }
}
