package org.ecommerce.productservice.services;

import org.ecommerce.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dbProductService")
public class ProductServiceDBImpl implements ProductService{
    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product getProduct(Long productId) {
        return null;
    }
}
