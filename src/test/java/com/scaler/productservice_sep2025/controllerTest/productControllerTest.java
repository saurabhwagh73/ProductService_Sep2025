package com.scaler.productservice_sep2025.controllerTest;

import com.scaler.productservice_sep2025.controllers.productController;
import com.scaler.productservice_sep2025.dtos.ProductDto;
import com.scaler.productservice_sep2025.models.Product;
import com.scaler.productservice_sep2025.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class productControllerTest {
    @Autowired
    private productController productcontroller;
    @MockitoBean
    private IProductService productService;
    @Test
    public void getProductByIdReturnsProductSuccefully(){
        //Arrange
        Long id=2L;
        Product product=new Product();
        product.setId(id);
        product.setName("santoor");
        product.setPrice(35.25);

        when(productService.getProductById(id)).thenReturn(product);
        //Act
        ResponseEntity<ProductDto> productDtoResponseEntity=productcontroller.getProductById(id);
        //Assert
        assertNotNull(productDtoResponseEntity);
        assertNotNull(productDtoResponseEntity.getBody());
        assertEquals(id,productDtoResponseEntity.getBody().getId());
        assertEquals(product.getName(),productDtoResponseEntity.getBody().getName());
    }
}
