package com.InditexEnterprice.price.domain.ports.in.price;

import com.InditexEnterprice.price.domain.models.Price;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;
import java.util.Optional;

public interface RetrievePrice {
    Optional<Price> getPrice(@PositiveOrZero Long priceId);
    List<Price> getAllPrices();
}
