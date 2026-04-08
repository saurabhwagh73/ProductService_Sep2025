package com.scaler.productservice_sep2025.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaler.productservice_sep2025.controllers.productController;
import com.scaler.productservice_sep2025.dtos.ProductDto;
import com.scaler.productservice_sep2025.models.Product;
import com.scaler.productservice_sep2025.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(productController.class)
public class ProductController_mvcTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    @Qualifier("productService")
    private IProductService productService;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void getAllProductsRunSuccessfully() throws Exception {
        //Arrange
        Product product1=new Product();
        product1.setId(1L);
        product1.setName("Lux Soap");
        Product product2=new Product();
        product2.setId(2L);
        product2.setName("Santoor Soap");

        List<Product> products=new ArrayList<>();
        products.add(product1);
        products.add(product2);
        when(productService.getAllProducts()).thenReturn(products);

        ProductDto productDto1=new ProductDto();
        productDto1.setId(1L);
        productDto1.setName("Lux Soap");
        ProductDto productDto2=new ProductDto();
        productDto2.setId(2L);
        productDto2.setName("Santoor Soap");
        List<ProductDto> productDtos=new ArrayList<>();
        productDtos.add(productDto1);
        productDtos.add(productDto2);

       String body=objectMapper.writeValueAsString(productDtos);
        //Act and assert
        mockMvc.perform(get("/allProducts"))
                .andExpect(status().isOk())
                .andExpect(content().string(body));
    }
    @Test
    public void createProductSuccessfully() throws Exception {
        //Arrange
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Lux Soap");
        productDto.setDescription("Even toned Glow Vitamin C+E");

        Product product = new Product();
        product.setId(2L);
        product.setName("Santoor Soap");
        product.setDescription("Chandan toned Glow Vitamin C+E");

        when(productService.createProduct(any(ProductDto.class)))
                .thenReturn(product);
        //Act and assert
        mockMvc.perform(post("/products")   // ✅ ensure this matches controller
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2));
    }
}
