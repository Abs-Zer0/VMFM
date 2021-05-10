package ru.lii.vmfm.http.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

public data class RegRequest(
        @NotBlank @Size(min = 4, max = 50) val username: String,
        @NotBlank @Size(max = 50) @Email val email: String,
        @NotBlank @Size(min = 8, max = 50) val password: String,
        @NotBlank @Size(min = 8, max = 50) val confirmPassword: String
)
