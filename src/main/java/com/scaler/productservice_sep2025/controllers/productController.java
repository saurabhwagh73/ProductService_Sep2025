package com.scaler.productservice_sep2025.controllers;

import com.scaler.productservice_sep2025.dtos.CategoryDto;
import com.scaler.productservice_sep2025.dtos.ProductDto;
import com.scaler.productservice_sep2025.models.Product;
import com.scaler.productservice_sep2025.services.fakeStoreProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class productController {
    @Autowired
    private fakeStoreProductService fakeStoreProductService;
//    private final fakeStoreProductService fakeStoreProductService;
//
//    public productController(fakeStoreProductService fakeStoreProductService) {
//        this.fakeStoreProductService = fakeStoreProductService;
//    }

//    @GetMapping("/printHello_Word")
//    public ArrayList<String> printHello_Word(){
//        ArrayList<String> name=new ArrayList<>();
//        for(int i=0;i<10;i++){
//            name.add("Hello World");
//        }
////        for(String s:name){
////            System.out.print(s+" ");
////        }
////        System.out.println();
//        return name;
//
//    }
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) throws IllegalAccessException {
        if(id<=0){
            throw new IllegalAccessException("product id available in between 1to 20");
        }
        Product product=fakeStoreProductService.getProductById(id);
        if(product==null){
            throw new IllegalAccessException("product not found");
        }
        ProductDto productDto=from(product);
        MultiValueMap<String,String> header=new LinkedMultiValueMap<>();
        header.add("id","Showing to Learners");
        return new ResponseEntity<>(productDto,header,HttpStatus.OK);
    }
    @GetMapping("/allProducts")
    public List<ProductDto> getAllProducts(){
        List<ProductDto> productDtos=new ArrayList<>();
        List<Product> products=fakeStoreProductService.getAllProducts();
        for(Product product:products){
            ProductDto productDto=from(product);
            productDtos.add(productDto);
        }
        return productDtos;
    }
    private ProductDto from(Product product){
        //product object transfer to the ProductDto
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImage_url(product.getImage_url());
        if(product.getCategory()!=null){
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(product.getCategory().getName());
            productDto.setCategoryDto(categoryDto);
        }
        return productDto;
    }
}
