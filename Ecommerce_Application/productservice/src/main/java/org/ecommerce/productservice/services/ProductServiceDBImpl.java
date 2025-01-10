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

    @Override
    public void deleteProduct(Long productId) {
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }
}
