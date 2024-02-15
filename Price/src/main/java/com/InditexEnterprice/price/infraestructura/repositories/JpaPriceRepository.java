package com.InditexEnterprice.price.infraestructura.repositories;

import com.InditexEnterprice.price.infraestructura.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {
    //TODO: Here we can add our personal finder methods instead of save, find, etc
}
