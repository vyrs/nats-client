package br.com.zup.nats.entrypoint.controller

import br.com.zup.nats.core.mapper.ProdutoConverter
import br.com.zup.nats.core.ports.ProdutoServicePort
import br.com.zup.nats.entrypoint.dto.ProdutoDto
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import javax.validation.Valid

@Validated
@Controller("/produto")
class ProdutoController(private val produtoService: ProdutoServicePort) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Post
    fun save(@Body @Valid request: ProdutoDto): ProdutoDto {

        logger.info("Salvando produto!")

        val saved = produtoService.saveProduto(ProdutoConverter.produtoDtoToProduto(request))

        return saved
    }
}