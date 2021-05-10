package ru.lii.vmfm.db.repository

import java.util.Optional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import ru.lii.vmfm.db.model.Role

interface RoleRepository : JpaRepository<Role, Long> {

    fun findByName(@Param("name") name: String): Optional<Role>

    /*@Query("SELECT u FROM role u WHERE u.name = 'ADMIN'")
    fun findAdmin():Optional<Role>

    @Query("SELECT u FROM role u WHERE u.name = 'USER'")
    fun findUser():Optional<Role>*/
}
