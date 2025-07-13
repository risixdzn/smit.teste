package smit.teste.crud.api.dtos.pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDTO(
        Integer id,
        LocalDateTime data,
        List<ItemPedidoResponseDTO> itens,
        BigDecimal total
) {
}
