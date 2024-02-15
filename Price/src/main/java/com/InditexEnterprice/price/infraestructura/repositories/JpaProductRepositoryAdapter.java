package com.InditexEnterprice.price.infraestructura.repositories;

import com.InditexEnterprice.price.domain.models.Price;
import com.InditexEnterprice.price.domain.models.Product;
import com.InditexEnterprice.price.domain.ports.out.PriceRepositoryPort;
import com.InditexEnterprice.price.domain.ports.out.ProductRepositoryPort;
import com.InditexEnterprice.price.infraestructura.entities.PriceEntity;
import com.InditexEnterprice.price.infraestructura.entities.ProductEntity;
import com.InditexEnterprice.price.infraestructura.mapers.PriceMapper;
import com.InditexEnterprice.price.infraestructura.mapers.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class JpaProductRepositoryAdapter implements ProductRepositoryPort {

    private final JpaProductRepository jpaProductRepository;
    private final ProductMapper productMapper;


    @Override
    public Product save(Product product) {
        ProductEntity productEntity = productMapper.fromDomainModel(product);
        ProductEntity savedProductEntity = jpaProductRepository.save(productEntity);
        return productMapper.toDomainModel(savedProductEntity);
    }
    @Override
    public Optional<Product> findById(Long productId) {
        return jpaProductRepository.findById(productId).map(productMapper::toDomainModel);
    }
    @Override
    public List<Product> findAll() {
        return jpaProductRepository.findAll().stream().map(productMapper::toDomainModel).toList();
    }
    @Override
    public Optional<Product> update(Product product) {
        if(jpaProductRepository.existsById(product.getId())){
            ProductEntity productEntity = productMapper.fromDomainModel(product);
            ProductEntity updateProductEntity = jpaProductRepository.save(productEntity);
            return Optional.of(productMapper.toDomainModel(updateProductEntity));
        }
        return Optional.empty();
    }
    @Override
    public boolean deletedById(Long productId) {
        return false;
    }


}
