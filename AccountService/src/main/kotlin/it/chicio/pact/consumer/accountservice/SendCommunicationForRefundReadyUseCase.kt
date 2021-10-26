package it.chicio.pact.consumer.accountservice

import arrow.core.Either

object RefundCommunicationSent

interface SendCommunicationForRefundReadyUseCase {
    fun execute(): Either<RefundReadyMessageConsumerError, RefundCommunicationSent>
}