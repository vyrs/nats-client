package br.com.zup.nats.entrypoint.controller

import br.com.zup.nats.core.model.Produto
import br.com.zup.nats.core.ports.ProdutoServicePort
import br.com.zup.nats.entrypoint.dto.ProdutoDto
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject
import javax.validation.ConstraintViolationException

@MicronautTest
class ProdutoControllerTest: AnnotationSpec() {
    val service = mockk<ProdutoServicePort>(relaxed = true)

    val produtoController = ProdutoController(service)

    lateinit var produto: Produto
    lateinit var produtoDto: ProdutoDto

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        produto = Produto(UUID.randomUUID(), "Notebook", "eletronicos", BigDecimal.TEN)
        produtoDto = ProdutoDto("Notebook", "eletronicos", BigDecimal.TEN)
    }

    @Inject
    lateinit var produtoControllerNew: ProdutoController

    @Test
    fun `nao deve cadastrar produto com nome vazio`() {
        val produto2 = ProdutoDto("", "categoria", BigDecimal.valueOf(200))

        val result = shouldThrow<ConstraintViolationException> {
            produtoControllerNew.save(produto2)
        }

        result.message shouldBe "save.request.nome: não deve estar em branco"
    }

    @Test
    fun `nao deve cadastrar produto com categoria vazia`() {
        val produto2 = ProdutoDto("nome", "", BigDecimal.valueOf(200))

        val result = shouldThrow<ConstraintViolationException> {
            produtoControllerNew.save(produto2)
        }

        result.message shouldBe "save.request.categoria: não deve estar em branco"
    }

    @Test
    fun `nao deve cadastrar produto com preço negativo`() {
        val produto2 = ProdutoDto("tgeg", "frt", BigDecimal.valueOf(-10))
        val request = HttpRequest.POST("/produto", produto2)

        val response = shouldThrow<HttpClientResponseException> {
            client.toBlocking().exchange(request, ProdutoDto::class.java)
        }

        response.message shouldBe "request.preco: deve ser maior que 0"
    }

    @Test
    fun `deve cadastrar novo produto`() {
        every { service.saveProduto(produto) } returns produtoDto

        val result = produtoController.save(produtoDto)

        result.shouldBeTypeOf<ProdutoDto>()
    }
}
