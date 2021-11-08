package it.chicio.pact.provider.refundservice

import arrow.core.right
import au.com.dius.pact.core.model.Interaction
import au.com.dius.pact.core.model.Pact
import au.com.dius.pact.provider.PactVerifyProvider
import au.com.dius.pact.provider.junit5.MessageTestTarget
import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider
import au.com.dius.pact.provider.junitsupport.Consumer
import au.com.dius.pact.provider.junitsupport.Provider
import au.com.dius.pact.provider.junitsupport.State
import au.com.dius.pact.provider.junitsupport.loader.PactBroker
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal

@ExtendWith(MockKExtension::class)
@Provider("refund-service")
@Consumer("account-service")
@PactBroker(host = "localhost", port = "80")
class RefundReadyMessageProducerTest {
    @MockK
    private lateinit var refundRepository: RefundRepository

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider::class)
    fun testTemplate(pact: Pact, interaction: Interaction, context: PactVerificationContext) {
        context.verifyInteraction()
    }

    @BeforeEach
    fun before(context: PactVerificationContext) {
        context.target = MessageTestTarget(packagesToScan = listOf(this::class.java.packageName))
    }

    @State("a refund ready to be sent to the user")
    @PactVerifyProvider("the refund data to be communicated")
    fun `produce refund ready message`(): String {
        every {
            refundRepository.get(123)
        } returns Refund(
            123,
            Money(
                BigDecimal(100),
                "EUR"
            )
        ).right()

        return RefundReadyMessageProducer(refundRepository)
            .produceFor(123)
            .fold(
                { "invalid message" },
                { it }
            )
    }
}