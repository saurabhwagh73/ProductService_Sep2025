package com.scaler.productservice_sep2025.clients;

import com.scaler.productservice_sep2025.dtos.fakeStoreProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class fakeStoreApi {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public fakeStoreProductDto getProductById(Long id) {
        ResponseEntity<fakeStoreProductDto> fakestoreProductDto= requestForEntity(HttpMethod.GET,"https://fakestoreapi.com/products/{id}",null, fakeStoreProductDto.class,id);
        if(validateResponse(fakestoreProductDto)){
            return fakestoreProductDto.getBody();
        }
        return null;
    }

    public fakeStoreProductDto[] getAllProducts() {
        ResponseEntity<fakeStoreProductDto[]> fakeStoreProductDtoResponseEntity=
                requestForEntity(HttpMethod.GET,"https://fakestoreapi.com/products",null, fakeStoreProductDto[].class);
        if(validateResponse(fakeStoreProductDtoResponseEntity)){
            fakeStoreProductDto[] fakestoreProductDto=fakeStoreProductDtoResponseEntity.getBody();
            return fakestoreProductDto;
        }
        return null;
    }

    public fakeStoreProductDto createProduct(fakeStoreProductDto fakeStoreProductDto) {
        ResponseEntity<fakeStoreProductDto> fakestoreProductDto=requestForEntity(HttpMethod.POST,"https://fakestoreapi.com/products",
                fakeStoreProductDto,fakeStoreProductDto.class);
        return fakestoreProductDto.getBody();
    }

    public fakeStoreProductDto replaceProduct(Long id, fakeStoreProductDto fakeProduct) {
        ResponseEntity<fakeStoreProductDto> fakeStoreProductDtoResponseEntity=
                requestForEntity(HttpMethod.PUT,"https://fakestoreapi.com/products/{id}",fakeProduct,
                        fakeStoreProductDto.class,id);
        if(validateResponse(fakeStoreProductDtoResponseEntity)){
            return fakeStoreProductDtoResponseEntity.getBody();
        }
        return null;
    }


    private <T> boolean validateResponse(ResponseEntity<T> fakeStoreProductDtoResponseEntity){
        if(fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200))
                && fakeStoreProductDtoResponseEntity.hasBody()){
            return true;
        }
        return false;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
}
