package com.scaler.productservice_sep2025.services;


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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class fakeStoreProductService implements IProductService{
    @Autowired
    RestTemplateBuilder restTemplateBuilder;
    @Override
    public Product getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<fakeStoreProductDto> fakestoreProductDto= restTemplate
                .getForEntity("https://fakestoreapi.com/products/{id}", fakeStoreProductDto.class,id);
        if(fakestoreProductDto.getStatusCode().equals(HttpStatusCode.valueOf(200))
                && fakestoreProductDto.hasBody()){
            return from(fakestoreProductDto.getBody());
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products=new ArrayList<>();
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<fakeStoreProductDto[]> fakeStoreProductDtoResponseEntity=
                restTemplate.getForEntity("https://fakestoreapi.com/products", fakeStoreProductDto[].class);
            //Get fakestoreproduct from ResponseEntity
        if(fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200))
        && fakeStoreProductDtoResponseEntity.hasBody()){
            fakeStoreProductDto[] fakestoreProductDto=fakeStoreProductDtoResponseEntity.getBody();
            for(fakeStoreProductDto f:fakestoreProductDto){
                Product product=from(f);
                products.add(product);
            }
        }

        return products;
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        fakeStoreProductDto fakeProduct=mapper(productDto);
        fakeStoreProductDto fakestoreProductDto=restTemplate.postForObject("https://fakestoreapi.com/products",
                fakeProduct,fakeStoreProductDto.class);
//        ResponseEntity<fakeStoreProductDto> fakeStoreProductDtoResponseEntity=
//                restTemplate.postForEntity("https://fakestoreapi.com/products",fakeProduct,
//                        fakeStoreProductDto.class);
//        if(fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200)) &&
//        fakeStoreProductDtoResponseEntity.hasBody()){
//            return from(fakeStoreProductDtoResponseEntity.getBody());
//        }
        return from(fakestoreProductDto);
    }

    @Override
    public Product replaceProduct(Long id, ProductDto productDto) {
        fakeStoreProductDto fakeProduct=mapper(productDto);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<fakeStoreProductDto> fakeStoreProductDtoResponseEntity=
                requestForEntity(HttpMethod.PUT,"https://fakestoreapi.com/products/{id}",fakeProduct,
                        fakeStoreProductDto.class,id);
        if(fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200))&&
        fakeStoreProductDtoResponseEntity.hasBody()){
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }
        return null;
    }
    public <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod,String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
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
