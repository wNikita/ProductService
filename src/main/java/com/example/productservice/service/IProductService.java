package com.example.productservice.service;

import com.example.productservice.dto.ProductDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface IProductService {

    ProductDTO createProduct(ProductDTO productDTO);

    List<ProductDTO> getAllProduct();

    ProductDTO getProductById(Long productId);

    List<ProductDTO> getProductBySupplierId(int userId);

    ProductDTO partialUpdate(ProductDTO productDTO);

    ProductDTO updateProduct(ProductDTO productDTO);

    List<ProductDTO> filterProducts(Specification specification, Pageable pageable);
}
