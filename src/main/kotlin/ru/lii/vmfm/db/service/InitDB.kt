package ru.lii.vmfm.db.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import ru.lii.vmfm.db.model.Role
import ru.lii.vmfm.db.repository.RoleRepository

@Component
public class DemoData : ApplicationRunner {

    @Autowired private lateinit var repo: RoleRepository

    @Throws(Exception::class)
    override fun run(args: ApplicationArguments) {
        repo.save(Role(1, "USER"))
        repo.save(Role(2, "ADMIN"))

        repo.flush()
    }
}
