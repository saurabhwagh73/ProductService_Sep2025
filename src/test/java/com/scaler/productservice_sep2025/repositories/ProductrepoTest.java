package com.scaler.productservice_sep2025.repositories;

import com.scaler.productservice_sep2025.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class ProductrepoTest {
    @Autowired
    Productrepo productrepo;
    @Test
    public void testQueries(){
//        List<Product> list = productrepo.findAll();
//        System.out.println(list.size());
//        System.out.println(list.get(0).getName());
        String description=productrepo.findProductDescriptionById(1L);
        System.out.println(description);
    }

}