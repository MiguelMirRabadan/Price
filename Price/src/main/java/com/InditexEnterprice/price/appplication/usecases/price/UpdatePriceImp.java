package com.InditexEnterprice.price.appplication.usecases.price;

import com.InditexEnterprice.price.domain.models.Price;
import com.InditexEnterprice.price.domain.ports.in.price.UpdatePrice;
import com.InditexEnterprice.price.domain.ports.out.PriceRepositoryPort;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
@AllArgsConstructor
public class UpdatePriceImp implements UpdatePrice {
    @Autowired
    private PriceRepositoryPort priceRepositoryPort;
    @Override
    public Optional<Price> updatePrice(@PositiveOrZero Long priceId, @NonNull Price updatedPrice) {
        return priceRepositoryPort.update(priceId, updatedPrice);
    }
}
