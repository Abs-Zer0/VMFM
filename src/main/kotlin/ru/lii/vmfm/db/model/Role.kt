package ru.lii.vmfm.db.model

import javax.persistence.*
import org.springframework.security.core.GrantedAuthority

@Entity
@Table(name = "roles")
data class Role(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long,
        @Column(name = "name") val name: String
) : GrantedAuthority {
    override fun getAuthority() = this.name
}
