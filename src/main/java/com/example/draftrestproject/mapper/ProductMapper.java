package com.example.draftrestproject.mapper;

import com.example.draftrestproject.domain.Product;
import com.example.draftrestproject.model.ProductRequest;
import com.example.draftrestproject.model.ProductResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toProductResponse(Product product);

    Product toProduct(ProductRequest productRequest);

}
