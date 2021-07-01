package br.com.zup.nats.core.ports

import br.com.zup.nats.core.model.Produto
import br.com.zup.nats.entrypoint.dto.ProdutoDto
import br.com.zup.nats.infrastructure.model.ProdutoEvent
import java.util.*
import javax.inject.Singleton

@Singleton
interface NatsServicePort {
    fun sendNatsToPost(produtoEvent: ProdutoEvent): ProdutoDto
    fun sendNatsToPut(produtoEvent: ProdutoEvent): ProdutoDto
    fun sendNatsToDelete(id: UUID)
}