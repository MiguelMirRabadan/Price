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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith({MockitoExtension.class})
class RetrievePriceImpTest {
    @Mock
    private PriceRepositoryPort priceRepositoryPort;
    @InjectMocks
    private RetrievePriceImp retrievePriceImp;
    private final Long PRICE_ID=5L;
    private final Long BRAND_ID = 6L;
    private final Long PRODUCT_ID = 1L;

    @Test
    void retrieveOneValidPrice() {
        Price price = new Price.Builder(LocalDateTime.now().withNano(0),
                this.BRAND_ID, this.PRODUCT_ID).build();

        Mockito.when(priceRepositoryPort.findById(PRICE_ID)).thenReturn(Optional.ofNullable(price));

        Optional<Price> priceFound = retrievePriceImp.getPrice(PRICE_ID);

        assertNotNull(priceFound);
        assertEquals(price, priceFound.get());

        Mockito.verify(priceRepositoryPort, times(1)).findById(PRICE_ID);
    }

    @Test
    void retrieveAllValidPrice() {
        Price price = new Price.Builder(LocalDateTime.now().withNano(0),
                this.BRAND_ID, this.PRODUCT_ID).build();
        Price price2 = new Price.Builder(LocalDateTime.now().withNano(0),
                this.BRAND_ID, this.PRODUCT_ID).build();

        List<Price> priceList = new ArrayList<>();
        priceList.add(price);
        priceList.add(price2);

        Mockito.when(priceRepositoryPort.findAll()).thenReturn(priceList);

        List<Price> pricesFound = retrievePriceImp.getAllPrices();

        assertNotNull(pricesFound);
        assertEquals(2, pricesFound.size());

        Mockito.verify(priceRepositoryPort, times(1)).findAll();
    }

    @Test
    @Disabled
    void retrieveNullPrice() {
        assertThrows(IllegalArgumentException.class, () -> {
            retrievePriceImp.getPrice(-1L);
        });
        verifyNoInteractions(priceRepositoryPort);
    }

}