package ru.lii.vmfm.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import ru.lii.vmfm.model.User

@Controller
class HomeController {
    @GetMapping("/", "/home") fun index() = "index"

    @GetMapping("/reg")
    fun getReg(model: Model): String {
        model.addAttribute("user", User())
        return "reg"
    }

    @PostMapping("/reg")
    fun postReg(@ModelAttribute("user") user: User): String {
        return "redirect:/"
    }
}
