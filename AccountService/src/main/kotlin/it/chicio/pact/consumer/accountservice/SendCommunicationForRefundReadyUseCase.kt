package it.chicio.pact.consumer.accountservice

import arrow.core.Either
import it.chicio.pact.consumer.accountservice.RefundReadyMessageConsumerError.CantCommunicateRefund

object RefundCommunicationSent

interface SendCommunicationForRefundReadyUseCase {
    fun execute(refundReadyMessage: RefundReadyMessage): Either<CantCommunicateRefund, RefundCommunicationSent>
}