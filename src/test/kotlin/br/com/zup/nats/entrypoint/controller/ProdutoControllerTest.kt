package br.com.zup.nats.entrypoint.controller

import br.com.zup.nats.core.model.Produto
import br.com.zup.nats.core.ports.ProdutoServicePort
import br.com.zup.nats.entrypoint.dto.ProdutoDto
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import java.util.*

@MicronautTest
class ProdutoControllerTest: AnnotationSpec() {
    val service = mockk<ProdutoServicePort>(relaxed = true)

    val produtoController = ProdutoController(service)

    lateinit var produto: Produto
    lateinit var produtoDto: ProdutoDto

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        produto = Produto(UUID.randomUUID(), "Notebook", "eletronicos", BigDecimal.TEN)
        produtoDto = ProdutoDto("Notebook", "eletronicos", BigDecimal.TEN)
    }

    @Test
    fun `deve cadastrar novo produto`() {
        every { service.saveProduto(produto) } returns produtoDto

        val result = produtoController.save(produtoDto)

        result.shouldBeTypeOf<ProdutoDto>()
    }
}
