package org.ecommerce.productservice.dtos.products;

import lombok.Getter;
import lombok.Setter;
import org.ecommerce.productservice.models.Product;

@Getter
@Setter
public class PatchProductResponseDto {
    private GetProductDto product;
}
