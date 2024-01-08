package com.example.productservice.controller;

import com.example.productservice.common.AppConstants;
import com.example.productservice.dto.APIResponse;
import com.example.productservice.dto.ProductDTO;
import com.example.productservice.exception.ApplicationException;
import com.example.productservice.model.Product;
import com.example.productservice.service.IProductService;
import com.example.productservice.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.productservice.common.AppConstants.BASE_URL;

@RestController
@RequestMapping(BASE_URL)
public class ProductController extends BaseController {
    private final ProductValidator productValidator;
    private final IProductService productService;

    public ProductController(@Qualifier("productValidator") ProductValidator productValidator, IProductService productService) {
        this.productValidator = productValidator;
        this.productService = productService;
    }


    @PostMapping
    public ResponseEntity<APIResponse<ProductDTO>> createProduct(@RequestBody ProductDTO productDTO, BindingResult bindingResult) {
        ResponseEntity<APIResponse<ProductDTO>> responseEntity;
        productValidator.validate(productDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ApplicationException(buildErrorResponse(bindingResult));
        }

        responseEntity = ResponseEntity.ok(prepareSuccessResponse(productService.createProduct(productDTO)));
        return responseEntity;
    }

//    @GetMapping
//    public ResponseEntity<APIResponse<List<ProductDTO>>> getAllProducts() {
//        ResponseEntity<APIResponse<List<ProductDTO>>> responseEntity;
//        responseEntity = ResponseEntity.ok(prepareSuccessResponse(productService
//                .getAllProduct()));
//        return responseEntity;
//    }
@GetMapping
public String getAllProducts() {
    return "Heloo get all products";
}
    @GetMapping(AppConstants.GET_PRODUCT_BY_ID)
    public ResponseEntity<APIResponse<ProductDTO>> getProductById(@PathVariable long productId) {
        ResponseEntity<APIResponse<ProductDTO>> responseEntity;
        responseEntity = ResponseEntity.ok(prepareSuccessResponse(productService.getProductById(productId)));
        return responseEntity;
    }

    @GetMapping(AppConstants.GET_PRODUCT_BY_SUPPLIER_ID)
    public ResponseEntity<APIResponse<List<ProductDTO>>> getProductBySupplierId(@RequestParam(name = "supplier") int supplierIdd) {
        ResponseEntity<APIResponse<List<ProductDTO>>> responseEntity;
        responseEntity = ResponseEntity.ok(prepareSuccessResponse(productService.getProductBySupplierId(supplierIdd)));
        return responseEntity;
    }

    @PutMapping(AppConstants.UPDATE_PRODUCT_BY_ID)
    public ResponseEntity<APIResponse<ProductDTO>> updateProduct(
            @RequestBody ProductDTO productDTO, BindingResult bindingResult) {
        ResponseEntity<APIResponse<ProductDTO>> responseEntity;
        productValidator.validate(productDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ApplicationException(buildErrorResponse(bindingResult));
        }
        responseEntity = ResponseEntity.ok(prepareSuccessResponse(productService.updateProduct(productDTO)));
        return responseEntity;
    }

    @PatchMapping(AppConstants.UPDATE_PRODUCT_BY_ID)
    public ResponseEntity<APIResponse<ProductDTO>> partialUpdate(@RequestBody ProductDTO productDTO) {
        ResponseEntity<APIResponse<ProductDTO>> responseEntity;
        responseEntity = ResponseEntity.ok(prepareSuccessResponse(productService.partialUpdate(productDTO)));
        return responseEntity;
    }

    @GetMapping(AppConstants.FILTER_PRODUCT)
    public ResponseEntity<APIResponse<List<ProductDTO>>> getProductsByFilers(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String price,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String quantity, Pageable pageable
    ) {
        Specification<Product> spec = Specification.where(null);

        if (category != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("category"), category));
        }
        if (price != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("price"), price));
        }
        if (name != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("name"), name));
        }
        if(quantity !=null)
        {
            spec=spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("quantity"),quantity));
        }
        ResponseEntity<APIResponse<List<ProductDTO>>> responseEntity;
        responseEntity = ResponseEntity.ok(prepareSuccessResponse(productService.filterProducts(spec, pageable)));
        return responseEntity;
    }

}