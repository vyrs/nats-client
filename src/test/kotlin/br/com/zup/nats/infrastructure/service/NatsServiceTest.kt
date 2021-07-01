package br.com.zup.nats.infrastructure.service

import br.com.zup.nats.core.model.Produto
import br.com.zup.nats.core.ports.NatsServicePort
import br.com.zup.nats.entrypoint.dto.ProdutoDto
import br.com.zup.nats.infrastructure.client.ProdutoClient
import br.com.zup.nats.infrastructure.model.EventInformation
import br.com.zup.nats.infrastructure.model.Events
import br.com.zup.nats.infrastructure.model.ProdutoEvent
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import java.util.*

class NatsServiceTest: AnnotationSpec() {

    val client = mockk<ProdutoClient>(relaxed = true)

    val natsService = NatsService(client)

    lateinit var produtoDto: ProdutoDto
    lateinit var produtoEvent: ProdutoEvent



    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        produtoDto = ProdutoDto("Notebook", "eletronicos", BigDecimal.TEN)
        produtoEvent = ProdutoEvent(UUID.randomUUID(), "Notebook", "eletronicos", BigDecimal.TEN)
    }

    @Test
    fun `deve enviar um produtoEvent para post e retornar um dto`() {

        val eventInformation = EventInformation(Events.SAVE_PRODUCT, produtoEvent)

        every { client.send(eventInformation) } returns Unit

        val result = natsService.sendNatsToPost(produtoEvent)

        result.shouldBeTypeOf<ProdutoDto>()
    }

    @Test
    fun `deve enviar um produtoEvent para put e retornar um dto`() {

        val eventInformation = EventInformation(Events.UPDATE_PRODUCT, produtoEvent)

        every { client.send(eventInformation) } returns Unit

        val result = natsService.sendNatsToPut(produtoEvent)

        result.shouldBeTypeOf<ProdutoDto>()
    }

    @Test
    fun `deve enviar um produtoEvent para delete`() {
        val id = UUID.randomUUID()
        val produtoEventToDelete = ProdutoEvent(id)

        val eventInformation = EventInformation(Events.DELETE_PRODUCT, produtoEventToDelete)

        every { client.send(eventInformation) } returns Unit

        val result = natsService.sendNatsToDelete(id)

        result.shouldBeTypeOf<Unit>()
    }

}
