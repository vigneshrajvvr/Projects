package org.ecommerce.productservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private long productId;
    private String title;
    private double price;
    private String description;
    private String imageUrl;
    private String category;
}
