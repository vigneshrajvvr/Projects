package org.ecommerce.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCreateProductResponseDto {
    private long productId;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
