package com.InditexEnterprice.price.appplication.usecases.price;

import com.InditexEnterprice.price.domain.models.Price;
import com.InditexEnterprice.price.domain.ports.out.PriceRepositoryPort;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith({MockitoExtension.class})
class DeletePriceImpTest {

    @Mock
    private PriceRepositoryPort priceRepositoryPort;
    @InjectMocks
    private DeletePriceImp deletePriceImp;
    private final Long PRICE_ID=5L;
    private final Long BRAND_ID = 6L;
    private final Long PRODUCT_ID = 1L;

    @Test
    void deleteValidPrice() {
        Price price = new Price.Builder(LocalDateTime.now().withNano(0),
                this.BRAND_ID, this.PRODUCT_ID).build();

        Mockito.when(priceRepositoryPort.deletedById(PRICE_ID)).thenReturn(true);

        boolean deletedPrice = deletePriceImp.deletePrice(PRICE_ID);

        assertTrue(deletedPrice);
        Mockito.verify(priceRepositoryPort, times(1)).deletedById(PRICE_ID);
    }

    @Test
    @Disabled
    void deleteNullPrice() {
        assertThrows(Exception.class, () -> {
            deletePriceImp.deletePrice(-1L);
        });
        verifyNoInteractions(priceRepositoryPort);
    }

}