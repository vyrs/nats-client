package br.com.zup.nats.core.ports

import br.com.zup.nats.core.model.Produto
import br.com.zup.nats.entrypoint.dto.ProdutoDto
import javax.inject.Singleton

@Singleton
interface ProdutoServicePort {
    fun saveProduto(produto: Produto): ProdutoDto
    fun updateProduto(produto: Produto): ProdutoDto
    fun deleteProduto(id: UUID)
}