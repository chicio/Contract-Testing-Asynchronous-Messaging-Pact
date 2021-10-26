package it.chicio.pact.provider.refundservice

import arrow.core.Either
import java.math.BigDecimal

data class Money(
    val value: BigDecimal,
    val currency: String
)

data class Refund(
    val refundId: Long,
    val amount: Money
)

interface RefundRepository {
    fun get(refundId: Long): Either<RefundReadyMessageProducerError.RefundRepositoryError, Refund>
}