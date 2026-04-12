
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        verify(productService,times(1)).getProductById(id);
    }
    @Test
    public void invalidIndexThrowsIllegalArugumentException(){
        //Arrange
        Long id=-1L;
        //Act and Assert
        Exception exception=assertThrows(IllegalArgumentException.class,()->productcontroller.getProductById(id));
        assertEquals("product id available in between 1to 20",exception.getMessage());
        verify(productService,times(0)).getProductById(id);
    }
    @Test
    public void productNotFoundThrowsRuntimeException(){
        //Arrange
        Long id=100L;
        when(productService.getProductById(id)).thenThrow(new RuntimeException("something went bad"));
        //Act and Assert
        assertThrows(RuntimeException.class,()->productcontroller.getProductById(id));
    }
}
