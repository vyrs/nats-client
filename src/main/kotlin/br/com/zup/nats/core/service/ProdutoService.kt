package br.com.zup.nats.core.service

import br.com.zup.nats.core.model.Produto
import br.com.zup.nats.core.ports.NatsServicePort
import br.com.zup.nats.core.ports.ProdutoServicePort
import br.com.zup.nats.entrypoint.dto.ProdutoDto
import javax.inject.Singleton

@Singleton
class ProdutoService(private val natsService: NatsServicePort): ProdutoServicePort {
    override fun saveProduto(produto: Produto): ProdutoDto {
        return natsService.sendNats(produto)
    }
}