package br.com.zup.nats.entrypoint.dto

import io.micronaut.core.annotation.Introspected
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive

@Introspected
data class ProdutoDto(
    @field:NotBlank val nome: String,
    @field:NotBlank val categoria: String,
    @field:Positive val preco: BigDecimal
)