package br.com.zup.nats.infrastructure.client

import br.com.zup.nats.core.model.Produto
import io.micronaut.nats.annotation.NatsClient
import io.micronaut.nats.annotation.Subject

@NatsClient
interface ProdutoClient {

    @Subject("produto")
    fun send(produto: Produto)
}