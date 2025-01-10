package org.ecommerce.productservice.dtos.products;

import lombok.Getter;
import lombok.Setter;
import org.ecommerce.productservice.models.Product;

import java.util.List;

@Getter
@Setter
public class GetAllProductResponseDto {
    private List<GetProductDto> products;
}
