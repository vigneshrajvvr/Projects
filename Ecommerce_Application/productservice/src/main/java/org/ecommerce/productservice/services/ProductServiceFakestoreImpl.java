package org.ecommerce.productservice.services;

import org.ecommerce.productservice.dtos.FakeStoreCreateProductRequestDto;
import org.ecommerce.productservice.dtos.FakeStoreCreateProductResponseDto;
import org.ecommerce.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreProductService")
public class ProductServiceFakestoreImpl implements ProductService{
    private RestTemplate restTemplate;

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
        FakeStoreCreateProductResponseDto fakeStoreCreateProductResponseDto = restTemplate.postForObject("https://fakestoreapi.com/products",
                fakeStoreCreateProductRequestDto,
                FakeStoreCreateProductResponseDto.class);

        Product createdProduct = new Product();
        createdProduct.setProductId(fakeStoreCreateProductResponseDto.getProductId());
        createdProduct.setTitle(fakeStoreCreateProductResponseDto.getTitle());
        createdProduct.setPrice(fakeStoreCreateProductRequestDto.getPrice());
        createdProduct.setDescription(fakeStoreCreateProductRequestDto.getDescription());
        createdProduct.setImageUrl(fakeStoreCreateProductRequestDto.getImage());
        createdProduct.setCategory(fakeStoreCreateProductRequestDto.getCategory());

        return createdProduct;
    }
}
