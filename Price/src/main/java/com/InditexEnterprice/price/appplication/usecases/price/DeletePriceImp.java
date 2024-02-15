package com.InditexEnterprice.price.appplication.usecases.price;

import com.InditexEnterprice.price.domain.ports.in.price.DeletePrice;
import com.InditexEnterprice.price.domain.ports.out.PriceRepositoryPort;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeletePriceImp implements DeletePrice {

    private final PriceRepositoryPort priceRepositoryPort;
    @Override
    public boolean deletePrice(@PositiveOrZero Long priceId) {
        return priceRepositoryPort.deletedById(priceId);
    }
}
