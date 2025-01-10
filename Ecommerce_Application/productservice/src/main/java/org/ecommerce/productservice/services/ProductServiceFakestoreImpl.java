package org.ecommerce.productservice.services;

import org.ecommerce.productservice.dtos.*;
import org.ecommerce.productservice.models.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service("fakeStoreProductService")
public class ProductServiceFakestoreImpl implements ProductService{
    private RestTemplate restTemplate;

    @Value("${fakestore.api.url}")
    private String fakeStoreUrl;

    public ProductServiceFakestoreImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product createProduct(Product product) {
        FakeStoreCreateProductRequestDto fakeStoreCreateProductRequestDto = new FakeStoreCreateProductRequestDto();
        fakeStoreCreateProductRequestDto.setTitle(product.getTitle());
        fakeStoreCreateProductRequestDto.setPrice(product.getPrice());
        fakeStoreCreateProductRequestDto.setDescription(product.getDescription());
        fakeStoreCreateProductRequestDto.setImage(product.getImageUrl());
        fakeStoreCreateProductRequestDto.setCategory(product.getCategory());
        FakeStoreCreateProductResponseDto fakeStoreCreateProductResponseDto = restTemplate.postForObject(fakeStoreUrl,
                fakeStoreCreateProductRequestDto,
                FakeStoreCreateProductResponseDto.class);

        Product createdProduct = new Product();
        System.out.println(fakeStoreCreateProductResponseDto.getProductId());
        createdProduct.setProductId(fakeStoreCreateProductResponseDto.getProductId());
        createdProduct.setTitle(fakeStoreCreateProductResponseDto.getTitle());
        createdProduct.setPrice(fakeStoreCreateProductRequestDto.getPrice());
        createdProduct.setDescription(fakeStoreCreateProductRequestDto.getDescription());
        createdProduct.setImageUrl(fakeStoreCreateProductRequestDto.getImage());
        createdProduct.setCategory(fakeStoreCreateProductRequestDto.getCategory());

        return createdProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreAllProductsResponseDto[] allProducts = restTemplate.getForObject(fakeStoreUrl, FakeStoreAllProductsResponseDto[].class);
        List<Product> allRetrievedProducts = new ArrayList<>();
        Arrays.stream(allProducts).forEach(product -> {
            allRetrievedProducts.add(product.toProduct());
        });
        return allRetrievedProducts;
    }

    @Override
    public Product getProduct(Long productId) {
        FakeStoreGetProductResponseDto fakeStoreGetProductResponseDto = restTemplate.getForObject(fakeStoreUrl + "/" + productId, FakeStoreGetProductResponseDto.class);
        return fakeStoreGetProductResponseDto.toProduct();
    }

    @Override
    public void deleteProduct(Long productId) {
        restTemplate.delete(fakeStoreUrl + "/" + productId);
    }

    @Override
    public Product updateProduct(Product product) {
        FakeStorePatchProductRequestDto fakeStorePatchProductRequestDto = new FakeStorePatchProductRequestDto();
        fakeStorePatchProductRequestDto.setProductId(product.getProductId());
        fakeStorePatchProductRequestDto.setDescription(product.getDescription());
        fakeStorePatchProductRequestDto.setCategory(product.getCategory());
        fakeStorePatchProductRequestDto.setPrice(product.getPrice());
        fakeStorePatchProductRequestDto.setTitle(product.getTitle());
        fakeStorePatchProductRequestDto.setImage(product.getImageUrl());

        HttpEntity<FakeStorePatchProductRequestDto> requestEntity = new HttpEntity<>(fakeStorePatchProductRequestDto);

        HttpEntity<FakeStorePatchProductReponseDto> fakeStorePatchProductReponseDto = restTemplate.exchange(fakeStoreUrl + "/" + product.getProductId(), HttpMethod.PATCH, requestEntity, FakeStorePatchProductReponseDto.class);

        return fakeStorePatchProductReponseDto.getBody().toProduct();
    }

}
