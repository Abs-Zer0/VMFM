package ru.lii.vmfm.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.ui.Model
import org.springframework.security.core.userdetails.UserDetails
import ru.lii.vmfm.db.model.User

@Controller
class HomeController {
    @GetMapping("/", "/home") fun index() = "index"
}
