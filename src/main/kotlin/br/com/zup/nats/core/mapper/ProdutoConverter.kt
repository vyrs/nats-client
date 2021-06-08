package br.com.zup.nats.core.mapper

import br.com.zup.nats.core.model.Produto
import br.com.zup.nats.entrypoint.dto.ProdutoDto
import java.util.*

class ProdutoConverter {
    companion object {
        fun produtoDtoToProduto(produtoDto: ProdutoDto) =
            Produto(UUID.randomUUID(), produtoDto.nome, produtoDto.categoria, produtoDto.preco)

        fun produtoToProdutoDto(produto: Produto) =
            ProdutoDto(produto.nome, produto.categoria, produto.preco)

    }
}