package com.example.draftrestproject.repository;

import com.example.draftrestproject.domain.Product;
import com.example.draftrestproject.model.Currency;
import com.example.draftrestproject.model.Language;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select p from Product p " +
            "where p.language = :language and p.currency = :currency and " +
            "((:name is null or p.name like %:name%) and " +
            "(:description is null or p.description like %:description%))")
    Page<Product> findByNameOrDescriptionAndLanguageAndCurrency(Pageable pageable,
                                                                @Param("name") String name,
                                                                @Param("description") String description,
                                                                @Param("language") Language language,
                                                                @Param("currency") Currency currency);

    Optional<Product> findByIdAndLanguageAndCurrency(Integer id, Language language, Currency currency);
}
