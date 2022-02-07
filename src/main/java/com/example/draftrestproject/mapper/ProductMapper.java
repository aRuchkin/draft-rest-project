package com.example.draftrestproject.mapper;

import com.example.draftrestproject.domain.Product;
import com.example.draftrestproject.model.ProductRequest;
import com.example.draftrestproject.model.ProductResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ProductMapper {

    /**
     * Mapping entity to API response
     */
    ProductResponse toProductResponse(Product product);

    /**
     * Mapping API request to entity
     */
    Product toProduct(ProductRequest productRequest);

}
