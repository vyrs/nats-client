package br.com.zup.nats.core.service

import br.com.zup.nats.core.model.Produto
import br.com.zup.nats.core.ports.NatsServicePort
import br.com.zup.nats.entrypoint.dto.ProdutoDto
import br.com.zup.nats.infrastructure.model.ProdutoEvent
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import java.util.*

class ProdutoServiceTest: AnnotationSpec() {

    val service = mockk<NatsServicePort>(relaxed = true)

    val produtoService = ProdutoService(service)

    lateinit var produto: Produto
    lateinit var produtoDto: ProdutoDto
    lateinit var produtoEvent: ProdutoEvent

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        produto = Produto(UUID.randomUUID(), "Notebook", "eletronicos", BigDecimal.TEN)
        produtoDto = ProdutoDto("Notebook", "eletronicos", BigDecimal.TEN)
        produtoEvent = ProdutoEvent(produto.id, produto.nome, produto.categoria, produto.preco)
    }

    @Test
    fun `deve enviar um produto para post e retornar um dto para a controller`() {
        every { service.sendNatsToPost(produtoEvent) } returns produtoDto

        val result = produtoService.saveProduto(produto)

        result.shouldBeTypeOf<ProdutoDto>()
    }

    @Test
    fun `deve enviar um produto para put e retornar um dto para a controller`() {
        every { service.sendNatsToPut(produtoEvent) } returns produtoDto

        val result = produtoService.updateProduto(produto)

        result.shouldBeTypeOf<ProdutoDto>()
    }

    @Test
    fun `deve enviar um id de um produto para ser deletado`() {
        val id = UUID.randomUUID()
        every { service.sendNatsToDelete(id) } returns Unit

        val result = produtoService.deleteProduto(id)

        result.shouldBeTypeOf<Unit>()
    }

}
