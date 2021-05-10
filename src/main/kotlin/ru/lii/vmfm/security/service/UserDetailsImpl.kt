package ru.lii.vmfm.security.service

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

public data class UserDetailsImpl(
        var id: Long? = null,
        var username: String? = null,
        var email: String? = null,
        @JsonIgnore var password: String? = null,
        var authorities: Collection<GrantedAuthority>? = null
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority>? = this.authorities

    override fun getPassword(): String? = this.password

    override fun getUsername(): String? = this.username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
