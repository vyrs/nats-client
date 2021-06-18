package br.com.zup.nats.infrastructure.model

class EventInformation (
    val event: Events = Events.UNKNOWN,
    val produto: ProdutoEvent = ProdutoEvent()
)