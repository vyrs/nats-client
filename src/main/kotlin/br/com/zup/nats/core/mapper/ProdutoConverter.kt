package br.com.zup.nats.core.mapper

import br.com.zup.nats.core.model.Produto
import br.com.zup.nats.entrypoint.dto.ProdutoDto
import br.com.zup.nats.infrastructure.model.ProdutoEvent
import java.util.*

class ProdutoConverter {
    companion object {
        fun produtoDtoToProduto(produtoDto: ProdutoDto) =
            Produto(null, produtoDto.nome, produtoDto.categoria, produtoDto.preco)

        fun produtoPutDtoToProduto(id: UUID, produtoDto: ProdutoDto) =
            Produto(id, produtoDto.nome, produtoDto.categoria, produtoDto.preco)

        fun produtoToProdutoDto(produto: Produto) =
            ProdutoDto(produto.nome, produto.categoria, produto.preco)

        fun produtoToProdutoEvent(produto: Produto) =
            ProdutoEvent(produto.id, produto.nome, produto.categoria, produto.preco)

        fun produtoEventToProdutoDto(produtoEvent: ProdutoEvent) =
            ProdutoDto(produtoEvent.nome, produtoEvent.categoria, produtoEvent.preco)

    }
}