package com.InditexEnterprice.price.infraestructura.repositories;

import com.InditexEnterprice.price.domain.models.Price;
import com.InditexEnterprice.price.domain.ports.out.PriceRepositoryPort;
import com.InditexEnterprice.price.infraestructura.entities.PriceEntity;
import com.InditexEnterprice.price.infraestructura.mapers.PriceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
@AllArgsConstructor
public class JpaPriceRepositoryAdapter implements PriceRepositoryPort {

    private final JpaPriceRepository jpaPriceRepository;

    private final PriceMapper priceMapper;

    @Override
    public Price save(Price price) {
        PriceEntity priceEntity = priceMapper.fromDomainModel(price);
        PriceEntity savedPriceEntity = jpaPriceRepository.save(priceEntity);
        return priceMapper.toDomainModel(savedPriceEntity);
    }
    @Override
    public Optional<Price> findById(Long priceId) {
        return jpaPriceRepository.findById(priceId).map(priceMapper::toDomainModel);
    }
    @Override
    public List<Price> findAll() {
        return jpaPriceRepository.findAll().stream().map(priceMapper::toDomainModel).toList();
    }
    @Override
    public Optional<Price> update(Long id, Price price) {
        if(jpaPriceRepository.existsById(id)){
            price.setPriceId(id);
            PriceEntity priceEntity = priceMapper.fromDomainModel(price);
            PriceEntity updatePriceEntity = jpaPriceRepository.save(priceEntity);
            return Optional.of(priceMapper.toDomainModel(updatePriceEntity));
        }
        return Optional.empty();
    }
    @Override
    public boolean deletedById(Long priceId) {
        if(jpaPriceRepository.existsById(priceId)) {
            jpaPriceRepository.deleteById(priceId);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
