package com.scaler.productservice_sep2025.services;


import com.scaler.productservice_sep2025.clients.fakeStoreApi;
import com.scaler.productservice_sep2025.dtos.ProductDto;
import com.scaler.productservice_sep2025.dtos.fakeStoreProductDto;
import com.scaler.productservice_sep2025.models.Category;
import com.scaler.productservice_sep2025.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
@Component("fakeStoreProducts")
public class fakeStoreProductService implements IProductService{
    @Autowired
    private fakeStoreApi fakeStoreApis;
    @Override
    public Product getProductById(Long id) {
        fakeStoreProductDto fakestoreProductDto= fakeStoreApis.getProductById(id);
        if(fakestoreProductDto!=null){
            return from(fakestoreProductDto);
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products=new ArrayList<>();
        fakeStoreProductDto[] fakeStoreProductDto= fakeStoreApis.getAllProducts();
        for(fakeStoreProductDto f:fakeStoreProductDto){
            Product product=from(f);
            products.add(product);
        }
        return products;
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        fakeStoreProductDto fakeProduct=mapper(productDto);
        fakeStoreProductDto fakestoreProductDto=fakeStoreApis.createProduct(fakeProduct);
        return from(fakestoreProductDto);
    }

    @Override
    public Product replaceProduct(Long id, ProductDto productDto) {
        fakeStoreProductDto fakeProduct=mapper(productDto);
        fakeStoreProductDto fakestoreProductDto=fakeStoreApis.replaceProduct(id,fakeProduct);
        if(fakestoreProductDto!=null){
            return from(fakestoreProductDto);
        }
        return null;
    }
    private Product from(fakeStoreProductDto fakestoreProductDto){
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
    public fakeStoreProductDto mapper(ProductDto productDto){
        fakeStoreProductDto fakestoreProductDto=new fakeStoreProductDto();
        fakestoreProductDto.setId(productDto.getId());
        fakestoreProductDto.setTitle(productDto.getName());
        fakestoreProductDto.setDescription(productDto.getDescription());
        fakestoreProductDto.setPrice(productDto.getPrice());
        fakestoreProductDto.setImage_url(productDto.getImage_url());
        fakestoreProductDto.setCategory(productDto.getCategoryDto().getName());
        return fakestoreProductDto;
    }
}
