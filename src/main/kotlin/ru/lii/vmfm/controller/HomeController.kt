package ru.lii.vmfm.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {
    @GetMapping("/", "/home") fun index() = "index"

    @GetMapping("/login") fun getLogin() = "login"

    @GetMapping("/auth") fun getAuth() = "auth"
}
