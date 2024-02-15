package com.InditexEnterprice.price.infraestructura.mapers;

import com.InditexEnterprice.price.domain.models.Price;
import com.InditexEnterprice.price.infraestructura.entities.PriceEntity;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class PriceMapper {
    @Autowired
    private ModelMapper modelMapper;

    public PriceEntity fromDomainModel(Price price){
        log.info("MAPPING from Entity to Domain Price");
        return modelMapper.map(price, PriceEntity.class);
    }

    public Price toDomainModel(PriceEntity priceEntity){
        log.info("MAPPING to Domain to Entity Price");
        return modelMapper.map(priceEntity, Price.class);
    }

}
