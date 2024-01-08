package com.example.productservice.service;

import com.example.productservice.dto.ProductDTO;
import com.example.productservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "productName", target = "name")
    @Mapping(source = "productDescription", target = "description")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "stockQuantity", target = "stockQuantity")
    ProductDTO productToProductDTO(Product product);

    @Mapping(source = "name", target = "productName")
    @Mapping(source = "description", target = "productDescription")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "stockQuantity", target = "stockQuantity")
    Product productDTOtoProduct(ProductDTO productDTO, @MappingTarget Product product);

    @Mapping(source = "productName", target = "name")
    @Mapping(source = "productDescription", target = "description")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "stockQuantity", target = "stockQuantity")
    ProductDTO productToProductDTO(Product product, @MappingTarget ProductDTO productDTO);
}
