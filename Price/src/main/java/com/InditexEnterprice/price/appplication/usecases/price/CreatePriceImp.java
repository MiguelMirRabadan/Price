package com.InditexEnterprice.price.appplication.usecases.price;

import com.InditexEnterprice.price.domain.models.Price;
import com.InditexEnterprice.price.domain.ports.in.price.CreatePrice;
import com.InditexEnterprice.price.domain.ports.out.PriceRepositoryPort;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
public class CreatePriceImp implements CreatePrice {
    @Autowired
    private PriceRepositoryPort priceRepositoryPort;
    @Override
    public Price createPrice(@NonNull Price price) {
        return priceRepositoryPort.save(price);
    }

    @Override
    public Price createPrice(@PositiveOrZero Long brandId, @PositiveOrZero Long productId, @DateTimeFormat LocalDateTime date) {
        return priceRepositoryPort.save(new Price.Builder(date,brandId,productId).build());

    }
}
