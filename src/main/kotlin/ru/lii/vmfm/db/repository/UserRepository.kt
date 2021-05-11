package ru.lii.vmfm.db.repository

import java.util.Optional
import javax.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import ru.lii.vmfm.db.model.User

interface UserRepository : JpaRepository<User, Long> {

    fun existsByUsername(@Param("username") username: String): Boolean

    fun findByUsername(@Param("username") username: String): Optional<User>

    fun existsByEmail(@Param("email") email: String): Boolean

    fun findByEmail(@Param("email") email: String): Optional<User>

    fun findByUsernameOrEmail(
            @Param("username") username: String,
            @Param("email") email: String
    ): Optional<User>

    @Transactional fun deleteByUsername(@Param("username") username: String)
}
