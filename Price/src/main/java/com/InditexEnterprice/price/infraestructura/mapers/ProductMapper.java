package com.InditexEnterprice.price.infraestructura.mapers;

import com.InditexEnterprice.price.domain.models.Product;
import com.InditexEnterprice.price.infraestructura.entities.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    @Autowired
    private ModelMapper modelMapper;

    public ProductEntity fromDomainModel(Product product){
        return modelMapper.map(product, ProductEntity.class);
    }

    public Product toDomainModel(ProductEntity productEntity){
        return modelMapper.map(productEntity, Product.class);
    }
}
