package it.chicio.pact.consumer.accountservice

import arrow.core.Either
import arrow.core.flatMap

sealed class RefundReadyMessageConsumerError {
    object CantExtractMessage: RefundReadyMessageConsumerError()
    object CantCommunicateRefund: RefundReadyMessageConsumerError()
}

class RefundReadyMessageConsumer(
    private val refundReadyMessagePayloadExtractor: RefundReadyMessagePayloadExtractor,
    private val sendCommunicationForRefundReadyUseCase: SendCommunicationForRefundReadyUseCase
) {
    fun consume(message: String): Either<RefundReadyMessageConsumerError, RefundCommunicationSent> =
        refundReadyMessagePayloadExtractor
            .extract(message)
            .flatMap {
                sendCommunicationForRefundReadyUseCase.execute()
            }
}