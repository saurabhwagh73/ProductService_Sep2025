package com.scaler.productservice_sep2025.repositories;

import com.scaler.productservice_sep2025.models.Category;
import com.scaler.productservice_sep2025.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CategoryrepoTest {
    @Autowired
    private Categoryrepo categoryrepo;
    @Test
    @Transactional
    public void testCategory(){
        Category category = categoryrepo.findById(2L).get();
        System.out.println(category.getName());
        //asking for product
        for(Product product : category.getProductList()){
            System.out.println(product.getName());
        }
    }
    @Test
    @Transactional
    public void testAllCategory(){
        List<Category> categories = categoryrepo.findAll();
        for(Category category : categories){
            System.out.println(category.getName());
            for(Product product : category.getProductList()){
                System.out.println(product.getName());
            }
        }
    }

}