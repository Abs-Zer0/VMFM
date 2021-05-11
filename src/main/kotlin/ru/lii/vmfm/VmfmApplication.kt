package ru.lii.vmfm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import ru.lii.vmfm.db.repository.RoleRepository
import ru.lii.vmfm.db.model.Role

@SpringBootApplication
class VmfmApplication{
}

fun main(args: Array<String>) {
	runApplication<VmfmApplication>(*args)
}
