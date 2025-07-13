package smit.teste.crud.api.dtos.pedido;

import java.util.UUID;

public record ItemPedidoResponseDTO(
        UUID produtoId,
        String nomeProduto,
        Integer quantidade
) {
}
