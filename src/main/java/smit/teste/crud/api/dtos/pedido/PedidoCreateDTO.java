package smit.teste.crud.api.dtos.pedido;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record PedidoCreateDTO(
        @NotNull(message = "A lista de itens é obrigatória")
        @Size(min = 1, message = "O pedido deve conter pelo menos um item")
        @Valid
        List<ItemPedidoCreateDTO> itens
) {
}
