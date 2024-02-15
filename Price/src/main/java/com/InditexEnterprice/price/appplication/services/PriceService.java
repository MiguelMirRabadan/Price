package com.InditexEnterprice.price.appplication.services;

import com.InditexEnterprice.price.domain.models.Price;
import com.InditexEnterprice.price.domain.ports.in.price.CreatePrice;
import com.InditexEnterprice.price.domain.ports.in.price.DeletePrice;
import com.InditexEnterprice.price.domain.ports.in.price.RetrievePrice;
import com.InditexEnterprice.price.domain.ports.in.price.UpdatePrice;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Getter
//@Service //*check AppConfig class
@AllArgsConstructor
public class PriceService implements CreatePrice, UpdatePrice,
                                        DeletePrice, RetrievePrice {


    private final CreatePrice createPrice;
    private final UpdatePrice updatePrice;
    private final DeletePrice deletePrice;
    private final RetrievePrice retrievePrice;

    @Override
    public Price createPrice(@NonNull Price price) {
        return createPrice.createPrice(price);
    }

    @Override
    public Price createPrice(@PositiveOrZero Long brandId, @PositiveOrZero Long productId, @DateTimeFormat LocalDateTime date) {
        return createPrice.createPrice(brandId,productId,date);
    }

    @Override
    public boolean deletePrice(@PositiveOrZero Long priceId) {
        return deletePrice.deletePrice(priceId);
    }

    @Override
    public Optional<Price> getPrice(@PositiveOrZero Long priceId) {
        return retrievePrice.getPrice(priceId);
    }

    @Override
    public List<Price> getAllPrices() {
        return this.retrievePrice.getAllPrices();
    }

    @Override
    public Optional<Price> updatePrice(@PositiveOrZero Long priceId, @NonNull Price updatedPrice) {
        return updatePrice.updatePrice(priceId, updatedPrice);
    }
}
