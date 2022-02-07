package com.example.draftrestproject.service;

import com.example.draftrestproject.domain.Product;
import com.example.draftrestproject.error.DataIncorrectException;
import com.example.draftrestproject.error.ProductNotFoundException;
import com.example.draftrestproject.mapper.ProductMapper;
import com.example.draftrestproject.model.ProductRequest;
import com.example.draftrestproject.model.ProductResponse;
import com.example.draftrestproject.repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class ProductService {

    private static final Logger log = LogManager.getLogger(Product.class);

    private final String NOT_FOUND_MESSAGE = "Product not found";
    private final String INCORRECT_PRODUCT_PRICE_MESSAGE = "The price must be greater than 0";
    private final String INCORRECT_PRODUCT_NAME_MESSAGE = "The name of product must be present";

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository,
                          ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductResponse find(Integer id) {
        return productMapper.toProductResponse(findOrThrow(id));
    }

    public ProductResponse create(ProductRequest productRequest) {
        checkRequest(productRequest);
        Product product = productMapper.toProduct(productRequest);
        product.setCreationDate(LocalDate.now());
        productRepository.save(product);
        return productMapper.toProductResponse(product);
    }

    public void delete(Integer id) {
        productRepository.delete(findOrThrow(id));
    }

    public ProductResponse update(Integer id, ProductRequest productRequest) {
        checkRequest(productRequest);
        Product product = findOrThrow(id);
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setCurrency(productRequest.getCurrency());
        product.setLanguage(productRequest.getLanguage());
        product.setModifyDate(LocalDate.now());
        return productMapper.toProductResponse(productRepository.save(product));
    }

    private Product findOrThrow(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> {
            log.error(NOT_FOUND_MESSAGE);
            return new ProductNotFoundException(NOT_FOUND_MESSAGE);
        });
    }

    private void checkRequest(ProductRequest productRequest) {
        if (productRequest.getName() == null || productRequest.getName().isEmpty()) {
            log.error(INCORRECT_PRODUCT_NAME_MESSAGE);
            throw new DataIncorrectException(INCORRECT_PRODUCT_NAME_MESSAGE);
        }
        if (productRequest.getPrice().compareTo(BigDecimal.valueOf(0)) <= 0) {
            log.error(INCORRECT_PRODUCT_PRICE_MESSAGE);
            throw new DataIncorrectException(INCORRECT_PRODUCT_PRICE_MESSAGE);
        }
    }

}
