package ru.lii.vmfm.security.service

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

public data class UserDetailsImpl(
        var uname: String? = null,
        @JsonIgnore var passwd: String? = null,
        var auths: Collection<GrantedAuthority>? = null
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority>? = this.auths

    override fun getPassword(): String? = this.passwd

    override fun getUsername(): String? = this.uname

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
