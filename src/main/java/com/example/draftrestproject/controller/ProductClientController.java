package com.example.draftrestproject.controller;

import com.example.draftrestproject.model.Currency;
import com.example.draftrestproject.model.Language;
import com.example.draftrestproject.model.ProductResponse;
import com.example.draftrestproject.service.ProductClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Product Controller for Client")
@RestController
@RequestMapping("/client/products")
public class ProductClientController extends BaseController {

    private final Integer DEFAULT_NUMBER_OF_PAGE = 0;
    private final Integer DEFAULT_LIMIT_ON_PAGE = 10;

    private final ProductClientService productClientService;

    public ProductClientController(ProductClientService productClientService) {
        this.productClientService = productClientService;
    }

    @Operation(summary = "Get product by id, language and currency")
    @GetMapping("/{id}")
    public ProductResponse findById(
            @Parameter(description = "productId") @PathVariable Integer id,
            @Parameter(description = "language") @RequestParam Language language,
            @Parameter(description = "currency") @RequestParam Currency currency) {
        return productClientService.find(id, language, currency);
    }

    @Operation(summary = "Get products by name or description, language and currency")
    @GetMapping()
    public Page<ProductResponse> findByNameOrDescription(
            @Parameter(description = "name") @RequestParam(required = false) String name,
            @Parameter(description = "description") @RequestParam(required = false) String description,
            @Parameter(description = "language") @RequestParam Language language,
            @Parameter(description = "currency") @RequestParam Currency currency,
            @Parameter(description = "number of page") @RequestParam(required = false) Integer numberOfPage,
            @Parameter(description = "number of elements on the page") @RequestParam(required = false) Integer limit) {
        return productClientService.findByNameOrDescription(
                createPageRequest(numberOfPage, limit),
                name,
                description,
                language,
                currency);
    }

    /**
     * Create valid Page Request if number of page or limit not present in request
     */
    private PageRequest createPageRequest(Integer numberOfPage, Integer limit) {
        return PageRequest.of(
                numberOfPage != null ? numberOfPage : DEFAULT_NUMBER_OF_PAGE,
                limit != null ? limit : DEFAULT_LIMIT_ON_PAGE);
    }

}
