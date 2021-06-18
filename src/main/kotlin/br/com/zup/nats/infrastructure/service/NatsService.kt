package br.com.zup.nats.infrastructure.service

import br.com.zup.nats.core.mapper.ProdutoConverter
import br.com.zup.nats.core.model.Produto
import br.com.zup.nats.core.ports.NatsServicePort
import br.com.zup.nats.entrypoint.dto.ProdutoDto
import br.com.zup.nats.infrastructure.client.ProdutoClient
import javax.inject.Singleton

@Singleton
class NatsService(private val client: ProdutoClient): NatsServicePort {
    
    private val logger = LoggerFactory.getLogger(this::class.java)

    
        override fun sendNatsToPost(produtoEvent: ProdutoEvent): ProdutoDto {

        val eventInformation = EventInformation(Events.SAVE_PRODUCT, produtoEvent)

        client.send(eventInformation)

        logger.info("Mensagem enviado para evento: ${eventInformation.event.event}")

        return ProdutoConverter.produtoEventToProdutoDto(produtoEvent)
    }

    override fun sendNatsToPut(produtoEvent: ProdutoEvent): ProdutoDto {
        val eventInformation = EventInformation(Events.UPDATE_PRODUCT, produtoEvent)

        client.send(eventInformation)

        logger.info("Mensagem enviado para evento: ${eventInformation.event.event}")

        return ProdutoConverter.produtoEventToProdutoDto(produtoEvent)
    }

    override fun sendNatsToDelete(id: UUID) {
        val eventInformation = EventInformation(Events.DELETE_PRODUCT, ProdutoEvent(id))

        client.send(eventInformation)

        logger.info("Mensagem enviado para evento: ${eventInformation.event.event}")
    }
}