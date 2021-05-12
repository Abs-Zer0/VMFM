package ru.lii.vmfm.db.service

import java.util.Optional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.lii.vmfm.db.model.User
import ru.lii.vmfm.db.repository.UserRepository

@Service
public class UserService : UserDetailsService {
    val logger: Logger = LoggerFactory.getLogger(UserService::class.java)

    @Autowired lateinit var users: UserRepository

    @Throws(UsernameNotFoundException::class)
    @Transactional
    override fun loadUserByUsername(username: String): UserDetails {
        val user: Optional<User> = getByUnameOrEmail(username)

        if (user.isEmpty) {
            throw UsernameNotFoundException(
                    "Пользователь не найден с таким именем или эл. почтой: $username")
        }

        return user.get().toUserDetails()
    }

    @Transactional
    fun getUser(username: String): Optional<User> {
        return users.findByUsername(username)
    }

    @Transactional
    fun getByUnameOrEmail(unameOrEmail: String): Optional<User> {
        return users.findByUsernameOrEmail(unameOrEmail, unameOrEmail)
    }

    fun existsByUsername(username: String): Boolean {
        return users.existsByUsername(username)
    }

    fun existsByEmail(email: String): Boolean {
        return users.existsByEmail(email)
    }

    @Transactional
    fun addOrUpdateUser(user: User): User {
        return users.saveAndFlush(user)
    }
}
