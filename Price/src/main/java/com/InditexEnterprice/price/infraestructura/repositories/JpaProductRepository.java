package com.InditexEnterprice.price.infraestructura.repositories;

import com.InditexEnterprice.price.infraestructura.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
    //TODO: Here we can add our personal finder methods instead of save, find, etc
}
