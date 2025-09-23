package com.scaler.productservice_sep2025.services;


import com.scaler.productservice_sep2025.dtos.fakeStoreProductDto;
import com.scaler.productservice_sep2025.models.Category;
import com.scaler.productservice_sep2025.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class fakeStoreProductService implements IProductService{
    @Autowired
    RestTemplateBuilder restTemplateBuilder;
    @Override
    public Product getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        fakeStoreProductDto fakestoreProductDto= restTemplate
                .getForObject("https://fakestoreapi.com/products/{id}", fakeStoreProductDto.class,id);

            //fakeStoreDto object transfer to the product object
            Product product = new Product();
            product.setId(fakestoreProductDto.getId());
            product.setName(fakestoreProductDto.getTitle());
            product.setDescription(fakestoreProductDto.getDescription());
            product.setPrice(fakestoreProductDto.getPrice());
            product.setImage_url(fakestoreProductDto.getImage_url());
            Category category = new Category();
            category.setName(fakestoreProductDto.getCategory());
            product.setCategory(category);

            return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }
}
