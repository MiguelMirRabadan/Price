package com.InditexEnterprice.price.infraestructura.mapers;

import com.InditexEnterprice.price.domain.models.Product;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ProductMapperTest {

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductMapper productMapper;

    private Product product = null;
    private ProductEntity productEntity =  null;
    private final Long PRICE_ID=5L;
    private final Long BRAND_ID = 6L;
    private final Long PRODUCT_ID = 1L;

    @BeforeEach
    void preparePrice(){
        product = new Product();
        product.setId(PRODUCT_ID);
        product.setDescription("TEST");

        productEntity = new ProductEntity();
        productEntity.setProductId(PRODUCT_ID);
        productEntity.setDescription("TEST");
    }

    @AfterEach
    void cleanup(){
        product = null;
        productEntity = null;
        System.gc();
    }

    @Test
    void fromDomainModel() {

        Mockito.when(modelMapper.map(product,ProductEntity.class)).thenReturn(productEntity);
        ProductEntity result = productMapper.fromDomainModel(product);
        assertNotNull(result);
        assertEquals(productEntity, result);
    }

    @Test
    void toDomainModel() {

        Mockito.when(modelMapper.map(productEntity,Product.class)).thenReturn(product);
        Product result = productMapper.toDomainModel(productEntity);
        assertNotNull(result);
        assertEquals(product, result);
    }

}