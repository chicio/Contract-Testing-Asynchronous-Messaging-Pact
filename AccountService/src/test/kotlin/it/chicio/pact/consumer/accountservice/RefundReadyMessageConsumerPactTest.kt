package it.chicio.pact.consumer.accountservice

import arrow.core.right
import au.com.dius.pact.consumer.MessagePactBuilder
import au.com.dius.pact.consumer.dsl.PactDslJsonBody
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import au.com.dius.pact.consumer.junit5.ProviderType
import au.com.dius.pact.core.model.annotations.Pact
import au.com.dius.pact.core.model.messaging.Message
import au.com.dius.pact.core.model.messaging.MessagePact
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal

@ExperimentalUnsignedTypes
@ExtendWith(PactConsumerTestExt::class, MockKExtension::class)
@PactTestFor(providerName = "refund-service", providerType = ProviderType.ASYNCH)
class RefundReadyMessageConsumerPactTest {
    @MockK
    private lateinit var refundReadyMessagePayloadExtractor: RefundReadyMessagePayloadExtractor
    @MockK
    private lateinit var sendCommunicationForRefundReadyUseCase: SendCommunicationForRefundReadyUseCase

    @Pact(consumer = "account-service", provider = "refund-service")
    fun refundReadyMessagePact(builder: MessagePactBuilder): MessagePact {
        val pactDslJsonBody = PactDslJsonBody()
        return builder
            .given("a refund ready to be sent to the user")
            .hasPactWith("refund-service")
            .expectsToReceive("the refund data to be communicated")
            .withContent(
                pactDslJsonBody
                    .numberType("refundId", 123)
                    .`object`(
                        "amount",
                        PactDslJsonBody()
                            .numberType("value", 100)
                            .stringType("currency", "EUR")
                    )
            )
            .toPact()
    }

    @Test
    @PactTestFor(pactMethod = "refundReadyMessagePact")
    fun `consume refund ready message`(messages: List<Message>) {
        every {
            refundReadyMessagePayloadExtractor.extract("{\"amount\":{\"currency\":\"EUR\",\"value\":100},\"refundId\":123}")
        } returns refundReadyMessage.right()
        every {
            sendCommunicationForRefundReadyUseCase.execute(refundReadyMessage)
        } returns RefundCommunicationSent.right()

        RefundReadyMessageConsumer(refundReadyMessagePayloadExtractor, sendCommunicationForRefundReadyUseCase)
            .consume(messages[0].contentsAsString()) shouldBe RefundCommunicationSent.right()

        verify(exactly = 1) {
            refundReadyMessagePayloadExtractor.extract("{\"amount\":{\"currency\":\"EUR\",\"value\":100},\"refundId\":123}")
            sendCommunicationForRefundReadyUseCase.execute(refundReadyMessage)
        }
    }

    private companion object {
        private val refundReadyMessage = RefundReadyMessage(123, Money(BigDecimal("100"), "EUR"))
    }
}