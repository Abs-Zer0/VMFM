package ru.lii.vmfm.http.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

public data class LoginRequest(
        @NotBlank @Size(max = 50) val usernameOrEmail: String,
        @NotBlank @Size(min = 8, max = 50) val password: String
)
