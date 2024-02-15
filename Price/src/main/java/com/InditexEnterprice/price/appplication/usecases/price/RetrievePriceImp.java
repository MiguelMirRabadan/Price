package com.InditexEnterprice.price.appplication.usecases.price;

import com.InditexEnterprice.price.domain.models.Price;
import com.InditexEnterprice.price.domain.ports.in.price.RetrievePrice;
import com.InditexEnterprice.price.domain.ports.out.PriceRepositoryPort;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RetrievePriceImp implements RetrievePrice {

    @Autowired
    private PriceRepositoryPort priceRepositoryPort;


    @Override
    public Optional<Price> getPrice(@PositiveOrZero Long priceId) {
        return priceRepositoryPort.findById(priceId);
    }

    @Override
    public List<Price> getAllPrices() {
        return priceRepositoryPort.findAll();
    }
}
