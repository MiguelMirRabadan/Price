package com.InditexEnterprice.price.infraestructura.repositories;

import com.InditexEnterprice.price.domain.models.Price;
import com.InditexEnterprice.price.infraestructura.entities.PriceEntity;
import com.InditexEnterprice.price.infraestructura.entities.ProductEntity;
import com.InditexEnterprice.price.infraestructura.mapers.PriceMapper;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class JpaPriceRepositoryAdapterTest {

    @Mock
    private JpaPriceRepository jpaPriceRepository;
    @Mock
    private PriceMapper priceMapper;
    @InjectMocks
    private JpaPriceRepositoryAdapter repositoryAdapter;

    private Price price = null;
    private PriceEntity priceEntity = null;

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

    @Test
    void save() {
        Mockito.when(priceMapper.fromDomainModel(any(Price.class))).thenReturn(new PriceEntity());
        Mockito.when(jpaPriceRepository.save(any(PriceEntity.class))).thenReturn(priceEntity);
        Mockito.when(priceMapper.toDomainModel(any(PriceEntity.class))).thenReturn(price);

        Price savedPrice = repositoryAdapter.save(price);

        assertNotNull(savedPrice);
        assertEquals(price.getPriceId(),savedPrice.getPriceId());
        assertEquals(price.getCurrency(), savedPrice.getCurrency());
    }

    @Test
    void findById() {

        Mockito.when(jpaPriceRepository.findById(PRICE_ID)).thenReturn(Optional.ofNullable(priceEntity));
        Mockito.when(priceMapper.toDomainModel(any(PriceEntity.class))).thenReturn(price);

        Optional<Price> foundPrice = repositoryAdapter.findById(PRICE_ID);

        assertNotNull(foundPrice);
        assertTrue(foundPrice.isPresent());
        assertEquals(priceMapper.toDomainModel(priceEntity), foundPrice.get());
    }

    @Test
    void findAll() {

        List<PriceEntity> priceEntities = new ArrayList<>();
        priceEntities.add(priceEntity);
        priceEntities.add(new PriceEntity());

        Mockito.when(jpaPriceRepository.findAll()).thenReturn(priceEntities);
        Mockito.when(priceMapper.toDomainModel(any(PriceEntity.class))).thenReturn(price);

        List<Price> foundPrice = repositoryAdapter.findAll();

        assertNotNull(foundPrice);
        assertEquals(priceEntities.size(), foundPrice.size());

        foundPrice.forEach(elem ->assertEquals(priceMapper.toDomainModel(priceEntity), elem));

    }

    @Test
    void update() {
        Price price2 = new Price.Builder(LocalDateTime.now().withNano(0),
                this.BRAND_ID, this.PRODUCT_ID)
                .addEndDate(null)
                .addPriceList(5L)
                .addPrice(1000L)
                .addCurrency("MOD")
                .build();

        Mockito.when(jpaPriceRepository.existsById(PRICE_ID)).thenReturn(true);
        Mockito.when(jpaPriceRepository.save(any(PriceEntity.class))).thenReturn(priceEntity);
        Mockito.when(priceMapper.toDomainModel(any(PriceEntity.class))).thenReturn(price);
        Mockito.when(priceMapper.fromDomainModel(any(Price.class))).thenReturn(priceEntity);

        Optional<Price> updatedPrice = repositoryAdapter.update(PRICE_ID, price2);

        assertNotNull(updatedPrice);
        assertTrue(updatedPrice.isPresent());
        assertEquals(price.getPriceId(),updatedPrice.get().getPriceId());
        assertEquals(price.getPrice(),updatedPrice.get().getPrice());
    }

    @Test
    void deletedById() {
        Mockito.when(jpaPriceRepository.existsById(PRICE_ID)).thenReturn(true);
        Mockito.doNothing().when(jpaPriceRepository).deleteById(PRICE_ID);

        boolean deleted = repositoryAdapter.deletedById(PRICE_ID);

        assertTrue(deleted);
        Mockito.verify(jpaPriceRepository).deleteById(PRICE_ID);
    }
}