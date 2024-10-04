package org.ecommerce.productservice.controllers;

import org.ecommerce.productservice.dtos.CreateProductRequestDto;
import org.ecommerce.productservice.dtos.CreateProductResponseDto;
import org.ecommerce.productservice.models.Product;
import org.ecommerce.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("")
    public CreateProductResponseDto createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {
        Product product = productService.createProduct(createProductRequestDto.toProduct());
        return CreateProductResponseDto.fromProduct(product);
    }

    @GetMapping("")
    public void getAllProducts() {

    }

    @GetMapping("/{id}")
    public void getSigleProduct(@PathVariable("id") Long productId) {

    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long productId) {

    }

    private void updateProduct(){}

    private void replaceProduct(){}

}
