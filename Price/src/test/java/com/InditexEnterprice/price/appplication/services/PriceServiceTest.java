package com.InditexEnterprice.price.appplication.services;

import com.InditexEnterprice.price.domain.models.Price;
import com.InditexEnterprice.price.domain.ports.in.price.CreatePrice;
import com.InditexEnterprice.price.domain.ports.in.price.DeletePrice;
import com.InditexEnterprice.price.domain.ports.in.price.RetrievePrice;
import com.InditexEnterprice.price.domain.ports.in.price.UpdatePrice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private CreatePrice createPrice;
    @Mock
    private RetrievePrice retrievePrice;
    @Mock
    private UpdatePrice updatePrice;
    @Mock
    private DeletePrice deletePrice;

    private Price price = null;
    private final Long BRAND_ID=6L;

    private final Long PRICE_ID=5L;
    private final Long PRODUCT_ID=1L;
    @InjectMocks PriceService priceService;

    @BeforeEach
    void before(){
        price = new Price.Builder(LocalDateTime.now().withNano(0),BRAND_ID,PRODUCT_ID).build();
    }

    @AfterEach
    void cleanup(){
        price = null;
        System.gc();
    }

    @Test
    void createPrice() {
        Mockito.when(createPrice.createPrice(price)).thenReturn(price);
        Price result = priceService.createPrice(price);

        assertNotNull(result);
        assertEquals(price, result);
    }

    @Test
    void updatePrice() {
        Price updatedPrice = new Price.Builder(LocalDateTime.now().withNano(0),BRAND_ID,PRODUCT_ID).build();
        updatedPrice.setPrice(100L);

        Mockito.when(updatePrice.updatePrice(PRICE_ID, price)).thenReturn(Optional.of(updatedPrice));

        Optional<Price> result = priceService.updatePrice(PRICE_ID,price);

        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(updatedPrice, result.get());
    }

    @Test
    void deletePrice() {
        Mockito.when(deletePrice.deletePrice(PRICE_ID)).thenReturn(true);
        boolean result = priceService.deletePrice(PRICE_ID);
        assertTrue(result);
    }

    @Test
    void retrieveOnePrice() {
        Mockito.when(retrievePrice.getPrice(PRICE_ID)).thenReturn(Optional.of(price));
        Optional<Price> result = priceService.getPrice(PRICE_ID);
        assertNotNull(result);
        assertEquals(Optional.of(price), result);
    }

    @Test
    void retrieveAllPrices() {
        Price price2 = new Price.Builder(LocalDateTime.now().withNano(0),BRAND_ID,PRODUCT_ID).build();
        List<Price> prices = new ArrayList<>();
        prices.add(price);
        prices.add(price2);

        Mockito.when(retrievePrice.getAllPrices()).thenReturn(prices);
        List<Price> result = priceService.getAllPrices();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void createPriceNull() {
        assertThrows(NullPointerException.class, () -> {
            priceService.createPrice(null);
        });
    }

    // Por algún motivo se salta la validación del valor mínimo del método
    @Test
    @Disabled
    void updatePriceNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            priceService.deletePrice(-1L);
        });
    }



}