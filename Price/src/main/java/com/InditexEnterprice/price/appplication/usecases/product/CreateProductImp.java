package com.InditexEnterprice.price.appplication.usecases.product;

import com.InditexEnterprice.price.domain.models.Product;
import com.InditexEnterprice.price.domain.ports.in.product.CreateProduct;
import com.InditexEnterprice.price.domain.ports.out.ProductRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
public class CreateProductImp implements CreateProduct {
    @Autowired
    private ProductRepositoryPort productRepositoryPort;
    @Override
    public Product createProduct(@NonNull Product product) {
        return productRepositoryPort.save(product);
    }
}
