package org.ecommerce.productservice.dtos;


import lombok.Getter;
import lombok.Setter;
import org.ecommerce.productservice.models.Product;

@Getter
@Setter
public class PatchProductRequestDto {
    private long productId;
    private String title;
    private double price;
    private String description;
    private String imageUrl;
    private String category;

    public Product toProduct() {
        Product product = new Product();
        product.setProductId(this.productId);
        product.setTitle(this.title);
        product.setPrice(this.price);
        product.setDescription(this.description);
        product.setImageUrl(this.imageUrl);
        product.setCategory(this.category);
        return product;
    }
}
