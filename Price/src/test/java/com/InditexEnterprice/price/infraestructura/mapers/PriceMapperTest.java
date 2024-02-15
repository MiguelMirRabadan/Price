package com.InditexEnterprice.price.infraestructura.mapers;

import com.InditexEnterprice.price.domain.models.Price;
import com.InditexEnterprice.price.infraestructura.entities.PriceEntity;
import com.InditexEnterprice.price.infraestructura.entities.ProductEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class PriceMapperTest {

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PriceMapper priceMapper;

    private Price price = null;
    private PriceEntity priceEntity =  null;
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


        priceEntity = new PriceEntity();
        priceEntity.setPriceId(price.getPriceId());
        priceEntity.setStartDate(price.getStartDate());
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
        price = null;
        priceEntity = null;
        System.gc();
    }

    @Test
    void fromDomainModel() {

        Mockito.when(modelMapper.map(price,PriceEntity.class)).thenReturn(priceEntity);
        PriceEntity result = priceMapper.fromDomainModel(price);
        assertNotNull(result);
        assertEquals(priceEntity, result);
    }

    @Test
    void toDomainModel() {

        Mockito.when(modelMapper.map(priceEntity,Price.class)).thenReturn(price);
        Price result = priceMapper.toDomainModel(priceEntity);
        assertNotNull(result);
        assertEquals(price, result);
    }
}