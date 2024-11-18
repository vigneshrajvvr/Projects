package org.ecommerce.productservice.services;

import org.ecommerce.productservice.dtos.FakeStoreAllProductsResponseDto;
import org.ecommerce.productservice.dtos.FakeStoreCreateProductRequestDto;
import org.ecommerce.productservice.dtos.FakeStoreCreateProductResponseDto;
import org.ecommerce.productservice.dtos.FakeStoreGetProductResponseDto;
import org.ecommerce.productservice.models.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        FakeStoreAllProductsResponseDto[] allProdcuts = restTemplate.getForObject(fakeStoreUrl, FakeStoreAllProductsResponseDto[].class);
        List<Product> allReterivedProducts = new ArrayList<>();
        Arrays.stream(allProdcuts).forEach(product -> {
            allReterivedProducts.add(product.toProduct());
        });
        return allReterivedProducts;
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
}
