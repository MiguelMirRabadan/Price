package com.InditexEnterprice.price.infraestructura.controllers;

import com.InditexEnterprice.price.appplication.services.PriceService;
import com.InditexEnterprice.price.domain.models.Price;
import com.InditexEnterprice.price.infraestructura.entities.PriceEntity;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@Log4j2
class PriceControllerTest {

    @Mock
    private PriceService priceService;
    @InjectMocks
    private PriceController priceController;

    private Price price = null;
    private PriceEntity priceEntity = null;

    private final Long PRICE_ID=5L;
    private final Long BRAND_ID = 6L;
    private final Long PRODUCT_ID = 1L;

    @BeforeEach
    void preparePrice(){
        log.info("PreparePrice");
        price = new Price.Builder(LocalDateTime.now().withNano(0),
                this.BRAND_ID, this.PRODUCT_ID)
                .addEndDate(null)
                .addPriceList(1L)
                .addPrice(100L)
                .addCurrency("EUR")
                .build();
    }

    @Test
    void createPrice() {
        Mockito.when(priceService.createPrice(any(Price.class))).thenReturn(price);
        ResponseEntity<Price> response = priceController.createPrice(price);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(price,response.getBody());
    }


    @Test
    void getPriceById() {
        Mockito.when(priceService.getPrice(PRICE_ID)).thenReturn(Optional.ofNullable(price));
        ResponseEntity<Price> response = priceController.getPriceById(PRICE_ID);
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(price,response.getBody());
    }

    @Test
    void getAllPrices() {
        List<Price> prices = new ArrayList<>();
        prices.add(price);
        Mockito.when(priceService.getAllPrices()).thenReturn(prices);
        ResponseEntity<List<Price>> response = priceController.getAllPrices();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(prices.size(),response.getBody().size());
    }

    @Test
    void updatePrice() {
        Mockito.when(priceService.updatePrice(PRICE_ID,price)).thenReturn(Optional.ofNullable(price));
        ResponseEntity<Price> response = priceController.updatePrice(PRICE_ID, price);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(price,response.getBody());
    }

    @Test
    void deletePrice() {
        Mockito.when(priceService.deletePrice(PRICE_ID)).thenReturn(true);
        ResponseEntity<Void> response = priceController.deletePrice(PRICE_ID);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}