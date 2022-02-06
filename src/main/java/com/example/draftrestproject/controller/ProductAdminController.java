package com.example.draftrestproject.controller;

import com.example.draftrestproject.model.ProductRequest;
import com.example.draftrestproject.model.ProductResponse;
import com.example.draftrestproject.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Product Controller for Administrator")
@RestController
@RequestMapping("/")
public class ProductAdminController {

    private final ProductService productService;

    public ProductAdminController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Get product by id")
    @GetMapping("/{id}")
    public ProductResponse find(@PathVariable Integer id) {
        return productService.find(id);
    }

    @Operation(summary = "Create new product")
    @PostMapping
    public ProductResponse create(@RequestBody ProductRequest productRequest) {
        return productService.create(productRequest);
    }

    @Operation(summary = "Delete product")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        productService.delete(id);
    }

    @Operation(summary = "Update existing product")
    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable Integer id, @RequestBody ProductRequest productRequest) {
        return productService.update(id, productRequest);
    }

}
