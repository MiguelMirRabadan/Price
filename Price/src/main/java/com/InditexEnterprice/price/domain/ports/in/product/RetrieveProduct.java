package com.InditexEnterprice.price.domain.ports.in.product;

import com.InditexEnterprice.price.domain.models.Price;
import com.InditexEnterprice.price.domain.models.Product;

import java.util.List;
import java.util.Optional;

public interface RetrieveProduct {
    Optional<Product> getProduct(Long productId);
    List<Product> getAllProducts();
}
