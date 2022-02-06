package com.example.draftrestproject.domain;

import com.example.draftrestproject.model.Currency;
import com.example.draftrestproject.model.Language;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDate creationDate;
    private LocalDate modifyDate;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Enumerated(EnumType.STRING)
    private Language language;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getModifyDate() {
        return modifyDate;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Language getLanguage() {
        return language;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setModifyDate(LocalDate modifyDate) {
        this.modifyDate = modifyDate;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
