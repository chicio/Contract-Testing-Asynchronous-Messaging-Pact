package it.chicio.pact.provider.refundservice

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.right
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

sealed class RefundReadyMessageProducerError {
    object RefundRepositoryError : RefundReadyMessageProducerError()
}

class RefundReadyMessageProducer(
    private val refundRepository: RefundRepository,
) {
    fun produceFor(refundId: Long): Either<RefundReadyMessageProducerError, String> =
        refundRepository
            .get(refundId)
            .flatMap {
                jacksonObjectMapper().writeValueAsString(it).right()
            }
}