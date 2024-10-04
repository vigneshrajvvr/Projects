package org.ecommerce.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.ecommerce.productservice.models.Product;
import org.springframework.web.service.annotation.GetExchange;

@Getter
@Setter
public class CreateProductResponseDto {
    private long productId;
    private String title;
    private double price;
    private String description;
    private String imageUrl;
    private String category;

    public static CreateProductResponseDto fromProduct(Product product) {
        CreateProductResponseDto createProductResponseDto = new CreateProductResponseDto();
        createProductResponseDto.setProductId(product.getProductId());
        createProductResponseDto.setTitle(product.getTitle());
        createProductResponseDto.setPrice(product.getPrice());
        createProductResponseDto.setDescription(product.getDescription());
        createProductResponseDto.setImageUrl(product.getImageUrl());
        createProductResponseDto.setCategory(product.getCategory());
        return createProductResponseDto;
    }
}
