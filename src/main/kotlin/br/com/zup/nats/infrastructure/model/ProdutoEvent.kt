package br.com.zup.nats.infrastructure.model

import java.math.BigDecimal
import java.util.*

class ProdutoEvent (
    val id: UUID? = null,
    val nome: String = "",
    val categoria: String = "",
    val preco: BigDecimal = BigDecimal.ZERO
)