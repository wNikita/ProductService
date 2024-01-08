package com.example.productservice.validator;

import com.example.productservice.dto.ProductDTO;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static com.example.productservice.common.MessageKeys.*;


@Component("productValidator")
public class ProductValidator implements Validator {
    public static final String PRODUCT_NAME = "name";
    public static final String PRODUCT_DESCRIPTION = "description";
    public static final String PRODUCT_CATEGORY = "category";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_STOCK_QUANTITY = "stockQuantity";

    public static final int PRODUCT_NAME_LENGTH = 50;
    public static final int PRODUCT_DESCRIPTION_LENGTH = 150;
    public static final int PRODUCT_CATEGORY_LENGTH = 50;
    public static final int PRODUCT_PRICE_LENGTH = 10;
    public static final int PRODUCT_STOCK_QUANTITY_LENGTH = 20;


    @Override
    public boolean supports(Class<?> clazz) {
        return ProductDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductDTO productDTO = (ProductDTO) target;
        validateMandatoryFields(productDTO, errors);
    }


    public void validateMandatoryFields(ProductDTO productDTO, Errors errors) {
        if (StringUtils.isBlank(productDTO.getName()))
            errors.rejectValue(PRODUCT_NAME, PRODUCT_NOT_EMPTY);
        else if (productDTO.getName().length() > PRODUCT_NAME_LENGTH)
            errors.rejectValue(PRODUCT_NAME, PRODUCT_NAME_GREATER_LENGTH);


        if (StringUtils.isBlank(productDTO.getDescription()))
            errors.rejectValue(PRODUCT_DESCRIPTION, PRODUCT_DESCRIPTION_NOT_EMPTY);
        else if (productDTO.getDescription().length() > PRODUCT_DESCRIPTION_LENGTH)
            errors.rejectValue(PRODUCT_DESCRIPTION, PRODUCT_DESCRIPTION_GREATER_LENGTH);

        if (StringUtils.isBlank(productDTO.getCategory()))
            errors.rejectValue(PRODUCT_CATEGORY, PRODUCT_CATEGORY_NOT_EMPTY);
        else if (productDTO.getCategory().length() > PRODUCT_CATEGORY_LENGTH)
            errors.rejectValue(PRODUCT_CATEGORY, PRODUCT_CATEGORY_GREATER_LENGTH);

        if (StringUtils.isBlank(productDTO.getPrice()))
            errors.rejectValue(PRODUCT_PRICE, PRODUCT_PRICE_NOT_EMPTY);
        else if (productDTO.getPrice().length() > PRODUCT_PRICE_LENGTH)
            errors.rejectValue(PRODUCT_PRICE, PRODUCT_PRICE_GREATER_LENGTH);


        if (StringUtils.isBlank(productDTO.getStockQuantity()))
            errors.rejectValue(PRODUCT_STOCK_QUANTITY, PRODUCT_STOCK_QUANTITY_NOT_EMPTY);
        else if (productDTO.getStockQuantity().length() > PRODUCT_STOCK_QUANTITY_LENGTH)
            errors.rejectValue(PRODUCT_STOCK_QUANTITY, PRODUCT_STOCK_QUANTITY_GREATER_LENGTH);


    }

}
