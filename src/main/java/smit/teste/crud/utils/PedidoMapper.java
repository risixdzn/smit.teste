package smit.teste.crud.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import smit.teste.crud.api.dtos.pedido.ItemPedidoResponseDTO;
import smit.teste.crud.api.dtos.pedido.PedidoResponseDTO;
import smit.teste.crud.models.ItemPedido;
import smit.teste.crud.models.Pedido;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PedidoMapper {
    @Mapping(source = "itens", target = "itens")
    @Mapping(target = "total", expression = "java(pedido.getTotal())")
    PedidoResponseDTO toDTO(Pedido pedido);

    @Mapping(source = "produto.id", target = "produtoId")
    @Mapping(source = "produto.nome", target = "nomeProduto")
    ItemPedidoResponseDTO toDTO(ItemPedido item);

    List<ItemPedidoResponseDTO> toItemDTOList(List<ItemPedido> itens);
}

