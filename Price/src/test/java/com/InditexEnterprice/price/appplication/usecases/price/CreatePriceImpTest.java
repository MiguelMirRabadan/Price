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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith({MockitoExtension.class})
class CreatePriceImpTest {

    @Mock
    private PriceRepositoryPort priceRepositoryPort;
    @InjectMocks
    private CreatePriceImp createPriceImp;

    private final Long BRAND_ID = 6L;
    private final Long PRICE_ID=5L;
    private final Long PRODUCT_ID = 1L;

    @Test
    void createValidPrice() {
        Price price = new Price.Builder(LocalDateTime.now().withNano(0),
                this.BRAND_ID, this.PRODUCT_ID).build();

        Mockito.when(priceRepositoryPort.save(price)).thenReturn(price);

        Price createdPrice = createPriceImp.createPrice(price);

        assertNotNull(createdPrice);
        assertEquals(price, createdPrice);

        Mockito.verify(priceRepositoryPort, times(1)).save(price);
    }

    @Test
    void createNullPrice() {
        assertThrows(NullPointerException.class, () -> {
            createPriceImp.createPrice(null);
        });
        verifyNoInteractions(priceRepositoryPort);
    }
}