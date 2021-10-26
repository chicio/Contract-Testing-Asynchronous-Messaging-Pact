package it.chicio.pact.consumer.accountservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AccountServiceApplication

fun main(args: Array<String>) {
	runApplication<AccountServiceApplication>(*args)
}
