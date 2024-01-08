package com.example.productservice.service;

import com.example.productservice.common.Message;
import com.example.productservice.dto.ProductDTO;
import com.example.productservice.exception.ResourceNotFoundException;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.productservice.common.MessageKeys.PRODUCT_NOT_AVAILABLE;
import static com.example.productservice.common.MessageKeys.PRODUCT_NOT_FOUND;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = ProductMapper.MAPPER.productDTOtoProduct(productDTO, new Product());
        productRepository.save(product);
        productDTO.setProductId(product.getProductId());
        return productDTO;
    }
    @Override
    public List<ProductDTO> getAllProduct() {
        List<Product> product = productRepository.findAll();
        if (product.isEmpty()) {
            throw new ResourceNotFoundException(Message.get(PRODUCT_NOT_AVAILABLE));
        }
        return product.stream().map(ProductMapper.MAPPER::productToProductDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long storeId) {

        Optional<Product> product = productRepository.findById(storeId);
        if (product.isEmpty())
            throw new ResourceNotFoundException(Message.get(PRODUCT_NOT_FOUND));
        return ProductMapper.MAPPER.productToProductDTO(product.get(), new ProductDTO());
    }

    @Override
    public List<ProductDTO> getProductBySupplierId(int supplierId) {
        List<Product> product = productRepository.findBySupplierId(supplierId);
        if (product.isEmpty())
            throw new ResourceNotFoundException(Message.get(PRODUCT_NOT_FOUND));
        return product.stream().map(ProductMapper.MAPPER::productToProductDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDTO partialUpdate(ProductDTO productDTO) {
        Optional<Product> product = productRepository.findByProductId(productDTO.getProductId());
        if (product.isEmpty())
            throw new ResourceNotFoundException(Message.get(PRODUCT_NOT_FOUND));
        Product oldProduct = product.get();
        if (!StringUtils.isBlank(productDTO.getName())) {
            oldProduct.setProductName(productDTO.getName());
        }
        if (!StringUtils.isBlank(productDTO.getStockQuantity())) {
            oldProduct.setStockQuantity(productDTO.getStockQuantity());
        }
        if (!StringUtils.isBlank(productDTO.getPrice())) {
            oldProduct.setPrice(productDTO.getPrice());
        }
        if (!StringUtils.isBlank(productDTO.getCategory())) {
            oldProduct.setCategory(productDTO.getCategory());
        }
        if (!StringUtils.isBlank(productDTO.getDescription())) {
            oldProduct.setProductDescription(productDTO.getDescription());
        }
        productRepository.save(oldProduct);
        return ProductMapper.MAPPER.productToProductDTO(oldProduct, new ProductDTO());
    }


    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        Optional<Product> product = productRepository.findByProductId(productDTO.getProductId());
        if (product.isEmpty())
            throw new ResourceNotFoundException(Message.get(PRODUCT_NOT_FOUND));
        Product oldProduct = product.get();
        oldProduct = ProductMapper.MAPPER.productDTOtoProduct(productDTO, oldProduct);
        productRepository.save(oldProduct);
        return productDTO;
    }

    @Override
    public List<ProductDTO> filterProducts(Specification specification, Pageable pageable) {
        List<Product> products = productRepository.findAll(specification, pageable).getContent();
        if (products.isEmpty()) {
            throw new ResourceNotFoundException(Message.get(PRODUCT_NOT_AVAILABLE));
        }
        return products.stream().map(ProductMapper.MAPPER::productToProductDTO).collect(Collectors.toList());
    }
}

