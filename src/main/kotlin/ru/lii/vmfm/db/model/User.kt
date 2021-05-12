package ru.lii.vmfm.db.model

import javax.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.lii.vmfm.security.service.UserDetailsImpl
import java.util.UUID

@Entity
@Table(name = "users")
public data class User(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: UUID? = UUID.randomUUID(),
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
    ) : this(UUID.randomUUID(), username, email, password, null)

    fun toUserDetails(): UserDetails {
        return UserDetailsImpl(this.username, this.password, this.roles)
    }
}
