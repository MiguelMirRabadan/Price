package com.InditexEnterprice.price.appplication.usecases.product;

import com.InditexEnterprice.price.domain.ports.in.product.DeleteProduct;
import com.InditexEnterprice.price.domain.ports.out.ProductRepositoryPort;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
@AllArgsConstructor
public class DeleteProductImp implements DeleteProduct {
    @Autowired
    private ProductRepositoryPort productRepositoryPort;

    @Override
    public boolean deleteProduct(@PositiveOrZero Long productId) {
        return productRepositoryPort.deletedById(productId);
    }
}
