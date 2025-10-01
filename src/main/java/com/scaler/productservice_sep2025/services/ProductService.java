package com.scaler.productservice_sep2025.services;

import com.scaler.productservice_sep2025.dtos.ProductDto;
import com.scaler.productservice_sep2025.models.Category;
import com.scaler.productservice_sep2025.models.Product;
import com.scaler.productservice_sep2025.repositories.Productrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Component("productService")
public class ProductService implements IProductService{
    @Autowired
    Productrepo productrepo;
    @Override
    public Product getProductById(Long id) {
        return productrepo.findById(id).get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productrepo.findAll();
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        Product product =from(productDto);
        return productrepo.save(product);
    }

    @Override
    public Product replaceProduct(Long id, ProductDto productDto) {
        Product product =from(productDto);
        product.setId(id);//JPA checked if id is existed then replace or newly added product
        product.getCategory().setId(productDto.getCategoryDto().getId());
        return productrepo.save(product);
    }
    public Product from(ProductDto productDto) {
        Product product=new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImage_url(productDto.getImage_url());
        product.setDescription(productDto.getDescription());
        if(productDto.getCategoryDto()!=null){
            Category category=new Category();
            category.setName(productDto.getCategoryDto().getName());
            category.setDescription(productDto.getCategoryDto().getDescription());
            product.setCategory(category);
        }
        return product;
    }
}
