package ru.lii.vmfm.db.service

import java.util.Base64
import java.util.UUID
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import ru.lii.vmfm.db.model.Role
import ru.lii.vmfm.db.model.User

@Component
public class InitDB : ApplicationRunner {

    val logger: Logger = LoggerFactory.getLogger(InitDB::class.java)

    @Autowired lateinit var roles: RoleService
    @Autowired lateinit var users: UserService

    @Autowired lateinit var passwordEnc: PasswordEncoder
    val base64Enc: Base64.Encoder = Base64.getEncoder()

    @Throws(Exception::class)
    override fun run(args: ApplicationArguments) {
        val adminRoles: Collection<Role> = listOf(Role(1, "USER"), Role(2, "ADMIN"))
        roles.addOrUpdateRoles(adminRoles)

        val username: String = base64Enc.encodeToString("admin".toByteArray())
        val password: String =
                passwordEnc.encode(base64Enc.encodeToString("password".toByteArray()))
        if (users.getUser(username).isEmpty) {
            logger.info("====    Adding admin account in db    ====")

            val admin = User(UUID.randomUUID(), username, "test@test.test", password, adminRoles)
            users.addOrUpdateUser(admin)

            logger.info("====    Added admin account in db $admin    ====")
        }
    }
}
