package lii.vmfm.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VmfmApplication

fun main(args: Array<String>) {
	runApplication<VmfmApplication>(*args)
}
