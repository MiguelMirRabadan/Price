package com.InditexEnterprice.price.appplication.usecases.product;

import com.InditexEnterprice.price.domain.models.Product;
import com.InditexEnterprice.price.domain.ports.in.product.RetrieveProduct;
import com.InditexEnterprice.price.domain.ports.out.ProductRepositoryPort;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
public class RetrieveProductImp implements RetrieveProduct {

    @Autowired
    private ProductRepositoryPort productRepositoryPort;
    @Override
    public Optional<Product> getProduct(@PositiveOrZero Long productId) {
        return productRepositoryPort.findById(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepositoryPort.findAll();
    }
}
