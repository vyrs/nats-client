package br.com.zup.nats.infrastructure.service

import br.com.zup.nats.core.mapper.ProdutoConverter
import br.com.zup.nats.core.model.Produto
import br.com.zup.nats.core.ports.NatsServicePort
import br.com.zup.nats.entrypoint.dto.ProdutoDto
import br.com.zup.nats.infrastructure.client.ProdutoClient
import javax.inject.Singleton

@Singleton
class NatsService(private val client: ProdutoClient): NatsServicePort {
    override fun sendNats(produto: Produto): ProdutoDto {
        client.send(produto)

        return ProdutoConverter.produtoToProdutoDto(produto)
    }
}