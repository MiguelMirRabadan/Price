package com.InditexEnterprice.price.domain.ports.out;

import com.InditexEnterprice.price.domain.models.Price;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PriceRepositoryPort {
    @Transactional
    Price save(Price price);
    Optional<Price> findById(Long priceId);
    List<Price> findAll();
    @Transactional
    Optional<Price> update(Long id, Price price);
    @Transactional
    boolean deletedById(Long priceId);
}
