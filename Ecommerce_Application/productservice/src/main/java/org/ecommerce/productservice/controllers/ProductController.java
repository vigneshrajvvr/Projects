package org.ecommerce.productservice.controllers;

import org.ecommerce.productservice.dtos.*;
import org.ecommerce.productservice.dtos.products.*;
import org.ecommerce.productservice.models.Product;
import org.ecommerce.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<GetProductDto> getAllProducts() {
        List<Product> allProducts = productService.getAllProducts();
        List<GetProductDto> allFakeStoreProducts = new ArrayList<>();
        allProducts.stream().forEach(product -> {
            allFakeStoreProducts.add(GetProductDto.from(product));
        });

        return allFakeStoreProducts;
    }

    @GetMapping("/{id}")
    public GetSingleProductResponseDto getSigleProduct(@PathVariable("id") Long productId) {
        Product singleProduct = productService.getProduct(productId);
        return GetSingleProductResponseDto.fromProduct(singleProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long productId) {
        productService.deleteProduct(productId);
    }

    @PatchMapping("")
    private PatchProductResponseDto updateProduct(@RequestBody PatchProductRequestDto patchProductRequestDto){
        Product updatedProduct = productService.updateProduct(patchProductRequestDto.toProduct());
        PatchProductResponseDto patchProductResponseDto = new PatchProductResponseDto();
        patchProductResponseDto.setProduct(patchProductResponseDto.getProduct().from(updatedProduct));
        return patchProductResponseDto;
    }

    private void replaceProduct(){}

}
