package ru.lii.vmfm.db.service

import java.util.Optional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.lii.vmfm.db.model.Role
import ru.lii.vmfm.db.repository.RoleRepository

@Service
public class RoleService {
    @Autowired lateinit var roles: RoleRepository

    @Throws(Exception::class)
    @Transactional
    fun getUser(): Role {
        val role: Optional<Role> = roles.findByName("USER")

        if (role.isEmpty) {
            throw Exception("Возникли неполадки на сервере")
        }

        return role.get()
    }

    @Throws(Exception::class)
    @Transactional
    fun getAdmin(): Role {
        val role: Optional<Role> = roles.findByName("ADMIN")

        if (role.isEmpty) {
            throw Exception("Возникли неполадки на сервере")
        }

        return role.get()
    }

    @Transactional
    fun addOrUpdateRoles(vararg newRoles: Role) {
        addOrUpdateRoles(newRoles.toList())
    }

    @Transactional
    fun addOrUpdateRoles(newRoles: Collection<Role>) {
        for (role in newRoles) {
            roles.save(role)
        }

        roles.flush()
    }
}
