package com.InditexEnterprice.price.infraestructura.entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({MockitoExtension.class})
class ProductEntityTest {
    @InjectMocks
    private ProductEntity productEntity = null;
    private final Long PRICE_ID=5L;
    private final Long BRAND_ID = 6L;
    private final Long PRODUCT_ID = 1L;

    @BeforeEach
    void preparePrice(){
        productEntity = new ProductEntity();
        productEntity.setProductId(PRODUCT_ID);
        productEntity.setDescription("TEST");
    }

    @AfterEach
    void cleanup(){
        productEntity = null;
        System.gc();
    }

    @Test
    @DisplayName("Validate modificate atribute works")
    void priceConstructModification(){
        productEntity.setDescription("MOD");
        assertEquals("MOD", productEntity.getDescription());
    }

}