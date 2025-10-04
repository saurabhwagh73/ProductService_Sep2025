package com.scaler.productservice_sep2025.repositories;

import com.scaler.productservice_sep2025.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Categoryrepo extends JpaRepository<Category,Long> {
    Optional<Category> findCategoryById(Long id);
}
