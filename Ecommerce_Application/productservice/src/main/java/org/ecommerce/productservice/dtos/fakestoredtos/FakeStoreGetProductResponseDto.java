package org.ecommerce.productservice.dtos.fakestoredtos;

import lombok.Getter;
import lombok.Setter;
import org.ecommerce.productservice.models.Product;

@Getter
@Setter
public class FakeStoreGetProductResponseDto {
    private Long id;
    private String title;
    private double price;
    private String image;
    private String description;
    private String category;

    public Product toProduct() {
        Product product1 = new Product();
        product1.setProductId(this.getId());
        product1.setTitle(this.getTitle());
        product1.setDescription(this.getDescription());
        product1.setImageUrl(this.getImage());
        product1.setCategory(this.getCategory());
        product1.setPrice(this.getPrice());

        return product1;
    }
}