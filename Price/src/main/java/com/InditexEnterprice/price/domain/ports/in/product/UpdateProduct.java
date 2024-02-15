package com.InditexEnterprice.price.domain.ports.in.product;

import com.InditexEnterprice.price.domain.models.Price;
import com.InditexEnterprice.price.domain.models.Product;

import java.util.Optional;

public interface UpdateProduct {
    Optional<Product> updateProduct(Long productId, Product updatedProduct);
}
