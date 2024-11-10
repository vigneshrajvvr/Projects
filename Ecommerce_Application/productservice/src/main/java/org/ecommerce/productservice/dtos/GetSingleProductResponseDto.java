package org.ecommerce.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.ecommerce.productservice.models.Product;

@Getter
@Setter
public class GetSingleProductResponseDto {
    private long productId;
    private String title;
    private double price;
    private String description;
    private String imageUrl;
    private String category;

    public static GetSingleProductResponseDto fromProduct(Product product) {
        GetSingleProductResponseDto getSingleProductResponseDto = new GetSingleProductResponseDto();
        getSingleProductResponseDto.setProductId(product.getProductId());
        getSingleProductResponseDto.setTitle(product.getTitle());
        getSingleProductResponseDto.setPrice(product.getPrice());
        getSingleProductResponseDto.setDescription(product.getDescription());
        getSingleProductResponseDto.setImageUrl(product.getImageUrl());
        getSingleProductResponseDto.setCategory(product.getCategory());
        return getSingleProductResponseDto;
    }
}
