package com.asmeduardo.dscommerce.mappers;

import com.asmeduardo.dscommerce.dtos.ProductDTO;
import com.asmeduardo.dscommerce.dtos.ProductMinDTO;
import com.asmeduardo.dscommerce.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDto(Product product);
    ProductMinDTO minToDto(Product product);
    Product toEntity(ProductDTO productDTO);

    @Mapping(target = "id", ignore = true)
    void updateProductFromDto(ProductDTO dto, @MappingTarget Product product);
}
