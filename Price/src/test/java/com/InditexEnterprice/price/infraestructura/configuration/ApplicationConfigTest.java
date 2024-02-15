package com.InditexEnterprice.price.infraestructura.configuration;

import com.InditexEnterprice.price.appplication.services.PriceService;
import com.InditexEnterprice.price.appplication.services.ProductService;
import com.InditexEnterprice.price.domain.ports.out.PriceRepositoryPort;
import com.InditexEnterprice.price.domain.ports.out.ProductRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@ExtendWith({MockitoExtension.class})
class ApplicationConfigTest {

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @InjectMocks
    private ApplicationConfig applicationConfig;

    @Test
    void testServiceBean(){
        PriceService priceService = applicationConfig.priceService(priceRepositoryPort);
        ProductService productService = applicationConfig.productService((productRepositoryPort));

        assertNotNull(priceService);
        assertNotNull(productService);
    }

}