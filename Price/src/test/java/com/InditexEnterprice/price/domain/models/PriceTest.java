package com.InditexEnterprice.price.domain.models;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PriceTest {

    private Price price = null;
    private final Long PRICE_ID=5L;
    private final Long BRAND_ID = 6L;
    private final Long PRODUCT_ID = 1L;

    @BeforeEach
    void preparePrice(){
        price = new Price.Builder(LocalDateTime.now().withNano(0),
                this.BRAND_ID, this.PRODUCT_ID)
                .addEndDate(null)
                .addPriceList(1L)
                .addPrice(100L)
                .addCurrency("EUR")
                .build();
    }

    @AfterEach
    void cleanup(){
        price = null;
        System.gc();
    }

    @Test
    void priceSuccefulConstruct(){
        String fromatPattern = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";
        Pattern pattern = Pattern.compile(fromatPattern);

        assertNotNull(price);
        assertTrue(pattern.matcher(price.getStartDate().toString()).matches(),"Formato incorrecto");
        assertEquals(100L, price.getPrice());
        assertEquals("EUR", price.getCurrency());
    }

    @Test
    @DisplayName("Validate that the 2 objects are not equals due to the id")
    void priceBuilderEquality(){
       Price price2 = new Price.Builder(LocalDateTime.now().withNano(0),
                this.BRAND_ID, this.PRODUCT_ID)
                .addEndDate(null)
                .addPriceList(1L)
                .addPrice(100L)
                .addCurrency("EUR")
                .build();

        assertNotNull(price);
        assertNotNull(price2);
        assertNotEquals(price2, price);

    }

    @Test
    @DisplayName("Validate error when ")
    @Disabled
    void priceBuilderValidation(){
        assertThrows(IllegalArgumentException.class, () ->{
            price.setStartDate(null);
        });
    }

    @Test
    @DisplayName("Validate modificate atribute works")
    void priceBuilderModification(){
        price.setPriceList(0L);
        assertEquals(0L,price.getPriceList());
    }

}