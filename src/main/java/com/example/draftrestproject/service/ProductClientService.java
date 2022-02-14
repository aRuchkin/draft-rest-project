package com.example.draftrestproject.service;

import com.example.draftrestproject.domain.Product;
import com.example.draftrestproject.error.ProductNotFoundException;
import com.example.draftrestproject.mapper.ProductMapper;
import com.example.draftrestproject.model.Currency;
import com.example.draftrestproject.model.Language;
import com.example.draftrestproject.model.ProductResponse;
import com.example.draftrestproject.repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Main product client service
 */
@Service
public class ProductClientService {

    private static final Logger log = LogManager.getLogger(Product.class);

    private final String NOT_FOUND_MESSAGE = "Product not found";

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductClientService(ProductRepository productRepository,
                                ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductResponse find(Integer id, Language language, Currency currency) {
        return productMapper.toProductResponse(findOrThrow(id, language, currency));
    }

    public Page<ProductResponse> findByNameOrDescription(PageRequest pageRequest,
                                                         String name,
                                                         String description,
                                                         Language language,
                                                         Currency currency) {
        Page<Product> pageProduct = productRepository.findByNameOrDescriptionAndLanguageAndCurrency(pageRequest, name, description, language, currency);
        return pageProductToPageProductResponse(pageRequest, pageProduct);
    }

    private Product findOrThrow(Integer id, Language language, Currency currency) {
        return productRepository.findByIdAndLanguageAndCurrency(id, language, currency).orElseThrow(() -> {
            log.error(NOT_FOUND_MESSAGE);
            return new ProductNotFoundException(NOT_FOUND_MESSAGE);
        });
    }

    private Page<ProductResponse> pageProductToPageProductResponse(PageRequest pageRequest, Page<Product> pageProduct) {
        return new PageImpl<>(pageProduct.stream().map(product ->
                productMapper.toProductResponse(product)).collect(Collectors.toList()),
                pageRequest,
                pageProduct.getTotalElements());
    }

}
