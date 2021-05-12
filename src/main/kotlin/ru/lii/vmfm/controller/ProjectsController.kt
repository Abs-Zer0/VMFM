package ru.lii.vmfm.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import ru.lii.vmfm.db.model.User
import ru.lii.vmfm.db.service.UserService

@Controller
class ProjectsController {
    @Autowired lateinit var users: UserService

    @GetMapping("/wspace") fun workspace(): String = "redirect:/wspace/projs"

    @GetMapping("/wspace/projs")
    fun index(model: Model): String {
        val auth: Authentication? = SecurityContextHolder.getContext().getAuthentication()

        if (auth != null) {
            try {
                val user: User = users.getByUnameOrEmail(auth.getName()).get()
                model.addAttribute("user", user)

                return "w-projects"
            } catch (e: Exception) {
                return "redirect:/"
            }
        }

        return "redirect:/"
    }
}
