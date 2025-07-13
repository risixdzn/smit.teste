package smit.teste.crud.api.dtos.pedido;

public record ItemPedidoResponseDTO(
        Integer produtoId,
        String nomeProduto,
        Integer quantidade
) {
}
