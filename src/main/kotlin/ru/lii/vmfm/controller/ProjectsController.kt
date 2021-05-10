package ru.lii.vmfm.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/wspace")
class ProjectsController {
    @GetMapping("projs") fun index(): String = "w-projects"
}
