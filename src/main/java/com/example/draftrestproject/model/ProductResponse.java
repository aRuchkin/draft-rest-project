package com.example.draftrestproject.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class ProductResponse extends ProductRequest {

    @Schema(description = "Product identifier", example = "150")
    private Integer id;
    @Schema(description = "Product creation date", example = "2022-02-01")
    private LocalDate creationDate;
    @Schema(description = "Product modification date", example = "2022-02-02")
    private LocalDate modifyDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDate modifyDate) {
        this.modifyDate = modifyDate;
    }
}
