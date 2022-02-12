package com.example.draftrestproject.controller;

import com.example.draftrestproject.model.ProductRequest;
import com.example.draftrestproject.model.ProductResponse;
import com.example.draftrestproject.service.ProductAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Product Controller for Administrator")
@RestController
@RequestMapping("/admin/products")
public class ProductAdminController extends BaseController {

    private final ProductAdminService productAdminService;

    public ProductAdminController(ProductAdminService productAdminService) {
        this.productAdminService = productAdminService;
    }

    @Operation(summary = "Get product by id")
    @GetMapping("/{id}")
    public ProductResponse find(@Parameter(description = "productId") @PathVariable Integer id) {
        return productAdminService.find(id);
    }

    @Operation(summary = "Create new product")
    @PostMapping
    public ProductResponse create(@RequestBody ProductRequest productRequest) {
        return productAdminService.create(productRequest);
    }

    @Operation(summary = "Delete product")
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "productId") @PathVariable Integer id) {
        productAdminService.delete(id);
    }

    @Operation(summary = "Update existing product")
    @PutMapping("/{id}")
    public ProductResponse update(@Parameter(description = "productId") @PathVariable Integer id,
                                  @RequestBody ProductRequest productRequest) {
        return productAdminService.update(id, productRequest);
    }

}
