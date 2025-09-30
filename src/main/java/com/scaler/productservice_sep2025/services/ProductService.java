package com.scaler.productservice_sep2025.services;

import com.scaler.productservice_sep2025.dtos.ProductDto;
import com.scaler.productservice_sep2025.models.Product;

import java.util.List;

public class ProductService implements IProductService{
    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, ProductDto productDto) {
        return null;
    }
}
