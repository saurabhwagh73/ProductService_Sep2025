package com.scaler.productservice_sep2025.services;

import com.scaler.productservice_sep2025.dtos.ProductDto;
import com.scaler.productservice_sep2025.models.Product;

import java.util.List;

public interface IProductService {
    public Product getProductById(Long id);
    public List<Product> getAllProducts();
    public Product createProduct(ProductDto productDto);
    public Product replaceProduct(Long id, ProductDto productDto);
}
