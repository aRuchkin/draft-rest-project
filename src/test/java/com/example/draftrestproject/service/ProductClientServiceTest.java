package com.example.draftrestproject.service;

import com.example.draftrestproject.model.Currency;
import com.example.draftrestproject.model.Language;
import com.example.draftrestproject.model.ProductResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
class ProductClientServiceTest {

    @Autowired
    private ProductClientService productClientService;

    private final PageRequest PAGE_REQUEST = PageRequest.of(0, 10);


    @Test
    public void findAllElementsSuccessfulTest() {
        Page<ProductResponse> result = productClientService.findByNameOrDescription(
                PAGE_REQUEST,
                "",
                "",
                Language.EN,
                Currency.USD
        );
        Assertions.assertEquals(2, result.getTotalElements());
    }

    @Test
    public void findByNameUnsuccessfulTest() {
        Page<ProductResponse> result = productClientService.findByNameOrDescription(
                PAGE_REQUEST,
                "absent element",
                "",
                Language.EN,
                Currency.USD
        );
        Assertions.assertEquals(0, result.getTotalElements());
    }

    @Test
    public void findByDescriptionSuccessfulTest() {
        Page<ProductResponse> result = productClientService.findByNameOrDescription(
                PAGE_REQUEST,
                "",
                "second",
                Language.EN,
                Currency.USD
        );
        Assertions.assertEquals(1, result.getTotalElements());
        Assertions.assertEquals("second product description", result.getContent().get(0).getDescription());
    }
}