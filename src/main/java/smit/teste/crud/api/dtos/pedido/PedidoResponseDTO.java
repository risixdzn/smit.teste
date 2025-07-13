package smit.teste.crud.api.dtos.pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PedidoResponseDTO(
        UUID id,
        LocalDateTime data,
        List<ItemPedidoResponseDTO> itens,
        BigDecimal total
) {
}
