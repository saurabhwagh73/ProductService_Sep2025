package com.scaler.productservice_sep2025.repositories;

import com.scaler.productservice_sep2025.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Categoryrepo extends JpaRepository<Category,Long> {
}
