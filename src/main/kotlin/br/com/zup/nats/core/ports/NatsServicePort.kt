package br.com.zup.nats.core.ports

import br.com.zup.nats.core.model.Produto
import br.com.zup.nats.entrypoint.dto.ProdutoDto
import javax.inject.Singleton

@Singleton
interface NatsServicePort {
    fun sendNats(produto: Produto): ProdutoDto
}