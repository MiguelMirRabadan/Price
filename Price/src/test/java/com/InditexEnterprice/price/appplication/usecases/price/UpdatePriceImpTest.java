package com.InditexEnterprice.price.appplication.usecases.price;

import com.InditexEnterprice.price.domain.models.Price;
import com.InditexEnterprice.price.domain.ports.out.PriceRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class UpdatePriceImpTest {
    @Mock
    private PriceRepositoryPort priceRepositoryPort;
    @InjectMocks
    private UpdatePriceImp updatePriceImp;
    private final Long PRICE_ID=5L;
    private final Long BRAND_ID = 6L;
    private final Long PRODUCT_ID = 1L;

    @Test
    void updateValidPrice() {
        Price price = new Price.Builder(LocalDateTime.now().withNano(0),
                this.BRAND_ID, this.PRODUCT_ID).build();

        Price price2 = new Price.Builder(LocalDateTime.now().withNano(0),
                this.BRAND_ID, this.PRODUCT_ID).build();
        price2.setPrice(100L);

        Mockito.when(priceRepositoryPort.update(PRICE_ID,price)).thenReturn(Optional.ofNullable(price2));

        Optional<Price> updatedPrice = updatePriceImp.updatePrice(PRICE_ID, price);

        assertNotNull(updatedPrice);
        assertTrue(updatedPrice.isPresent());
        assertEquals(price2, updatedPrice.get());

        Mockito.verify(priceRepositoryPort, times(1)).update(PRICE_ID,price);
    }

    @Test
    void updateNullPrice() {
        assertThrows(NullPointerException.class, () -> {
            updatePriceImp.updatePrice(PRICE_ID, null);
        });
        verifyNoInteractions(priceRepositoryPort);
    }

}