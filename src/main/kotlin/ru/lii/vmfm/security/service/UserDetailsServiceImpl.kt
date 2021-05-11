package ru.lii.vmfm.security.service

import java.util.Optional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.lii.vmfm.db.model.User
import ru.lii.vmfm.db.repository.UserRepository

@Service
public class UserDetailsServiceImpl : UserDetailsService {
    @Autowired lateinit var userRepository: UserRepository

    @Throws(UsernameNotFoundException::class)
    @Transactional
    override fun loadUserByUsername(username: String): UserDetails {

        val user: Optional<User> = userRepository.findByUsernameOrEmail(username, username)

        if (user.isEmpty) {
            throw UsernameNotFoundException(
                    "Пользователь не найден с таким именем или эл. почтой: $username")
        }

        return user.get().toUserDetails()
    }
}
