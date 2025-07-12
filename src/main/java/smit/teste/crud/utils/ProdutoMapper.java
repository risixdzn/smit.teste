package smit.teste.crud.utils;

import org.mapstruct.*;
import smit.teste.crud.api.dtos.produto.ProdutoCreateDTO;
import smit.teste.crud.api.dtos.produto.ProdutoUpdateDTO;
import smit.teste.crud.models.Produto;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProdutoMapper {
    Produto toEntity(ProdutoCreateDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateFromDto(ProdutoUpdateDTO dto, @MappingTarget Produto entity);
}
