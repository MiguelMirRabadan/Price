package com.InditexEnterprice.price.appplication.services;

import com.InditexEnterprice.price.domain.models.Product;
import com.InditexEnterprice.price.domain.ports.in.product.CreateProduct;
import com.InditexEnterprice.price.domain.ports.in.product.DeleteProduct;
import com.InditexEnterprice.price.domain.ports.in.product.RetrieveProduct;
import com.InditexEnterprice.price.domain.ports.in.product.UpdateProduct;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;
//@Service * check AppConfig class
@Getter @AllArgsConstructor
public class ProductService implements CreateProduct, UpdateProduct, DeleteProduct, RetrieveProduct {

    private final CreateProduct createProduct;
    private final UpdateProduct updateProduct;
    private final RetrieveProduct retrieveProduct;
    private final DeleteProduct deleteProduct;

    @Override
    public Product createProduct(@NonNull Product product) {
        return createProduct.createProduct(product);
    }

    @Override
    public boolean deleteProduct(@PositiveOrZero Long productId) {
        return deleteProduct.deleteProduct(productId);
    }

    @Override
    public Optional<Product> getProduct(@PositiveOrZero Long productId) {
        return retrieveProduct.getProduct(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return retrieveProduct.getAllProducts();
    }

    @Override
    public Optional<Product> updateProduct(@PositiveOrZero Long productId, @NonNull Product updatedProduct) {
        return updateProduct.updateProduct(productId,updatedProduct);
    }
}
