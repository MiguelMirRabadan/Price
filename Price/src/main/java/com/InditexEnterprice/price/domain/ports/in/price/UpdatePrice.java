package com.InditexEnterprice.price.domain.ports.in.price;

import com.InditexEnterprice.price.domain.models.Price;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.NonNull;

import java.util.Optional;

public interface UpdatePrice {
    Optional<Price> updatePrice(@PositiveOrZero Long priceId, @NonNull Price updatedPrice);
}
