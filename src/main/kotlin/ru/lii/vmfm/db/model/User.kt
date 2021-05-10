package ru.lii.vmfm.db.model

import javax.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.lii.vmfm.security.service.UserDetailsImpl

@Entity
@Table(name = "user")
public data class User(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long? = 0,
        @Column(name = "username") var username: String? = null,
        @Column(name = "email") var email: String? = null,
        @Column(name = "password") var password: String? = null,
        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "user_role",
                joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")])
        var roles: Collection<Role>? = null
) {
    constructor(
            username: String?,
            email: String?,
            password: String?,
    ) : this(0, username, email, password, null)

    fun toUserDetails(): UserDetails {
        val auths: Collection<GrantedAuthority>? =
                roles?.map { r -> SimpleGrantedAuthority(r.name) }

        return UserDetailsImpl(this.id, this.username, this.email, this.password, auths)
    }
}
