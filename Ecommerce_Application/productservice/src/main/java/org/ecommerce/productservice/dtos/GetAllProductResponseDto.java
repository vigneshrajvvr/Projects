package org.ecommerce.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.ecommerce.productservice.models.Product;

@Getter
@Setter
public class GetAllProductResponseDto {
    private long productId;
    private String title;
    private double price;
    private String description;
    private String imageUrl;
    private String category;

    public static GetAllProductResponseDto fromProduct(Product product) {
        GetAllProductResponseDto getAllProductResponseDto = new GetAllProductResponseDto();
        getAllProductResponseDto.setProductId(product.getProductId());
        getAllProductResponseDto.setTitle(product.getTitle());
        getAllProductResponseDto.setPrice(product.getPrice());
        getAllProductResponseDto.setDescription(product.getDescription());
        getAllProductResponseDto.setImageUrl(product.getImageUrl());
        getAllProductResponseDto.setCategory(product.getCategory());
        return getAllProductResponseDto;
    }
}
