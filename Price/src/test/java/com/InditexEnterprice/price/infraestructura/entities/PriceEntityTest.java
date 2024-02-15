package com.InditexEnterprice.price.infraestructura.entities;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith({MockitoExtension.class})
class PriceEntityTest {
    @InjectMocks
    private PriceEntity priceEntity = null;
    private final Long PRICE_ID=5L;
    private final Long BRAND_ID = 6L;
    private final Long PRODUCT_ID = 1L;

    @BeforeEach
    void preparePrice(){
        priceEntity = new PriceEntity();
        priceEntity.setPriceId(priceEntity.getPriceId());
        priceEntity.setStartDate(priceEntity.getStartDate());
        priceEntity.setBrandId(BRAND_ID);
        priceEntity.setEndDate(null);
        priceEntity.setPriceList(1L);
        priceEntity.setCurrency("EUR");
        priceEntity.setPrice(100L);
        ProductEntity product = new ProductEntity();
        product.setProductId(PRODUCT_ID);
        priceEntity.setProduct(product);
    }

    @AfterEach
    void cleanup(){
        priceEntity = null;
        System.gc();
    }

    @Test
    @DisplayName("Validate that the entity doesn´t works without product fk")
    @Disabled
    void priceConstructValidation(){
        assertThrows(JdbcSQLIntegrityConstraintViolationException.class, () ->{
            priceEntity.setProduct(null);
        });
    }

    @Test
    @DisplayName("Validate modificate atribute works")
    void priceConstructModification(){
        priceEntity.setPriceList(0L);
        assertEquals(0L, priceEntity.getPriceList());
    }


}