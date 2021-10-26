package it.chicio.pact.consumer.accountservice

import arrow.core.Either
import java.math.BigDecimal

data class Money(
    val value: BigDecimal,
    val currency: String
)

data class Message(
    val refundId: Long,
    val amount: Money
)

interface RefundReadyMessagePayloadExtractor {
    fun extract(message: String): Either<RefundReadyMessageConsumerError, Message>
}