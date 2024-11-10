package org.ecommerce.productservice.controllers;

import org.ecommerce.productservice.dtos.CreateProductRequestDto;
import org.ecommerce.productservice.dtos.CreateProductResponseDto;
import org.ecommerce.productservice.dtos.GetAllProductResponseDto;
import org.ecommerce.productservice.models.Product;
import org.ecommerce.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    @Autowired
    public ProductController(@Value("${org.ecommerce.productservice.fakestore.impl}") String implementation, ApplicationContext applicationContext) {
        this.productService = (ProductService) applicationContext.getBean(implementation);
    }

    @PostMapping("")
    public CreateProductResponseDto createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {
        Product product = productService.createProduct(createProductRequestDto.toProduct());
        return CreateProductResponseDto.fromProduct(product);
    }

    @GetMapping("")
    public List<GetAllProductResponseDto> getAllProducts() {
        List<Product> allProducts = productService.getAllProducts();
        List<GetAllProductResponseDto> allFakeStoreProducts = new ArrayList<>();
        allProducts.stream().forEach(product -> {
            allFakeStoreProducts.add(GetAllProductResponseDto.fromProduct(product));
        });

        return allFakeStoreProducts;
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
