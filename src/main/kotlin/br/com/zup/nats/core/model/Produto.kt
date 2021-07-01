package br.com.zup.nats.core.model

import java.math.BigDecimal
import java.util.*

class Produto(
    val id: UUID? = null,
    val nome: String,
    val categoria: String,
    val preco: BigDecimal
)