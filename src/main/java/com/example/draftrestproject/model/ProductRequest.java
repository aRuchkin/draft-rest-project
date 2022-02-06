package com.example.draftrestproject.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class ProductRequest {

    @Schema(description = "Product name", required = true, example = "Laptop №1")
    private String name;
    @Schema(description = "Product description", example = "Gaming Laptop")
    private String description;
    @Schema(description = "Product price", example = "1200.50")
    private BigDecimal price;
    @Schema(description = "Product currency", example = "USD")
    private Currency currency;
    @Schema(description = "Product language", example = "EN")
    private Language language;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
