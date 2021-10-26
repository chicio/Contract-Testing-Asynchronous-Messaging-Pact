package it.chicio.pact.consumer.accountservice

import arrow.core.Either
import it.chicio.pact.consumer.accountservice.RefundReadyMessageConsumerError.CantExtractMessage
import java.math.BigDecimal

data class Money(
    val value: BigDecimal,
    val currency: String
)

data class RefundReadyMessage(
    val refundId: Long,
    val amount: Money
)

interface RefundReadyMessagePayloadExtractor {
    fun extract(message: String): Either<CantExtractMessage, RefundReadyMessage>
}