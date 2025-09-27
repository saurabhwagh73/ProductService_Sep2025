package com.scaler.productservice_sep2025.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Product extends JpaRepository<Product, Long> {
}
