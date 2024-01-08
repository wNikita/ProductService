package com.example.productservice.repository;

import com.example.productservice.dto.ProductDTO;
import com.example.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> ,JpaSpecificationExecutor{

    Optional<Product> findByProductId(int productId);



    List<Product> findBySupplierId(long supplierID);
}
