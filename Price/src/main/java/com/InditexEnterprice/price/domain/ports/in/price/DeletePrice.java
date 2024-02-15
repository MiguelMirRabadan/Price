package com.InditexEnterprice.price.domain.ports.in.price;

import jakarta.validation.constraints.PositiveOrZero;

public interface DeletePrice {

    boolean deletePrice(@PositiveOrZero Long priceId);
}
