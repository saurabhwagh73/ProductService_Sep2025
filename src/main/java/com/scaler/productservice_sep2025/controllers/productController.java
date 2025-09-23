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
    @GetMapping("/getProductById/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        Product product=fakeStoreProductService.getProductById(id);
        //product object transfer to the ProductDto
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(product.getCategory().getName());
        categoryDto.setDescription(product.getCategory().getDescription());
        productDto.setCategoryDto(categoryDto);
        //handling multithreaded
        MultiValueMap<String,String> header=new LinkedMultiValueMap<>();
        header.add("showing to ","Learners");
        return new ResponseEntity<>(productDto,header, HttpStatus.OK);

    }
}
