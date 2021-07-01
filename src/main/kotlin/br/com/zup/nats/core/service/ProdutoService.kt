package br.com.zup.nats.core.service

import br.com.zup.nats.core.mapper.ProdutoConverter
import br.com.zup.nats.core.model.Produto
import br.com.zup.nats.core.ports.NatsServicePort
import br.com.zup.nats.core.ports.ProdutoServicePort
import br.com.zup.nats.entrypoint.dto.ProdutoDto
import java.util.*
import javax.inject.Singleton

@Singleton
class ProdutoService(private val natsService: NatsServicePort): ProdutoServicePort {

    override fun saveProduto(produto: Produto): ProdutoDto {
        return natsService.sendNatsToPost(ProdutoConverter.produtoToProdutoEvent(produto))
    }

    override fun updateProduto(produto: Produto): ProdutoDto {
        return natsService.sendNatsToPut(ProdutoConverter.produtoToProdutoEvent(produto))
    }

    override fun deleteProduto(id: UUID) {
        natsService.sendNatsToDelete(id)
    }
}