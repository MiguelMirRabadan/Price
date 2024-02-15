package com.InditexEnterprice.price.domain.ports.in.price;

import com.InditexEnterprice.price.domain.models.Price;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public interface CreatePrice {
    Price createPrice(@NonNull Price price);
    Price createPrice(@PositiveOrZero Long brandId, @PositiveOrZero Long productId, @DateTimeFormat LocalDateTime date);
}
