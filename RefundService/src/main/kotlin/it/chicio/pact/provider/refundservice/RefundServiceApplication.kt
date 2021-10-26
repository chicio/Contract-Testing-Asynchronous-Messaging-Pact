package it.chicio.pact.provider.refundservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RefundServiceApplication

fun main(args: Array<String>) {
	runApplication<RefundServiceApplication>(*args)
}
