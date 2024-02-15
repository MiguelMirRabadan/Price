package com.InditexEnterprice.price.appplication.usecases.product;

import com.InditexEnterprice.price.domain.models.Product;
import com.InditexEnterprice.price.domain.ports.in.product.UpdateProduct;
import com.InditexEnterprice.price.domain.ports.out.ProductRepositoryPort;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
@AllArgsConstructor
public class UpdateProductImp implements UpdateProduct {
    @Autowired
    private ProductRepositoryPort productRepositoryPort;
    @Override
    public Optional<Product> updateProduct(@PositiveOrZero Long productId, @NonNull Product updatedProduct) {
        return productRepositoryPort.update(updatedProduct);
    }
}
