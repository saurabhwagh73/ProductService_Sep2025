package com.scaler.productservice_sep2025.repositories;

import com.scaler.productservice_sep2025.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Productrepo extends JpaRepository<Product, Long> {
    public Product save(Product product);
    public Optional<Product> findById(Long id);
}