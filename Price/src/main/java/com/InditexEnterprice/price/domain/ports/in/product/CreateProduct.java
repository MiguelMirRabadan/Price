package com.InditexEnterprice.price.domain.ports.in.product;

import com.InditexEnterprice.price.domain.models.Product;

public interface CreateProduct {
    Product createProduct(Product product);
}
