package org.ecommerce.productservice.services;

import org.ecommerce.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    List<Product> getAllProducts();
}
