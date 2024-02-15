package com.InditexEnterprice.price.appplication.services;

import com.InditexEnterprice.price.domain.models.Product;
import com.InditexEnterprice.price.domain.ports.in.product.CreateProduct;
import com.InditexEnterprice.price.domain.ports.in.product.DeleteProduct;
import com.InditexEnterprice.price.domain.ports.in.product.RetrieveProduct;
import com.InditexEnterprice.price.domain.ports.in.product.UpdateProduct;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private CreateProduct createProduct;
    @Mock
    private RetrieveProduct retrieveProduct;
    @Mock
    private UpdateProduct updateProduct;
    @Mock
    private DeleteProduct deleteProduct;

    private Product product = null;
    private final Long PRODUCT_ID=1L;
    @InjectMocks
    ProductService productService;

    @BeforeEach
    void before(){
        product = new Product();
    }

    @AfterEach
    void cleanup(){
        product = null;
        System.gc();
    }

    @Test
    void createProduct() {
        Mockito.when(createProduct.createProduct(product)).thenReturn(product);
        Product result = productService.createProduct(product);

        assertNotNull(result);
        assertEquals(product, result);
    }

    @Test
    void updateProduct() {
        Product updatedProduct = new Product();
        updatedProduct.setDescription("Nuevo producto");

        Mockito.when(updateProduct.updateProduct(PRODUCT_ID, product)).thenReturn(Optional.of(updatedProduct));

        Optional<Product> result = productService.updateProduct(PRODUCT_ID, product);

        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(updatedProduct, result.get());
    }

    @Test
    void deleteProduct() {
        Mockito.when(deleteProduct.deleteProduct(PRODUCT_ID)).thenReturn(true);
        boolean result = productService.deleteProduct(PRODUCT_ID);

        assertTrue(result);
    }

    @Test
    void retrieveOneProduct() {
        Mockito.when(retrieveProduct.getProduct(PRODUCT_ID)).thenReturn(Optional.of(product));
        Optional<Product> result = productService.getProduct(PRODUCT_ID);

        assertNotNull(result);
        assertEquals(Optional.of(product), result);
    }

    @Test
    void retrieveAllProduct() {
        Product product2 = new Product();
        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product2);

        Mockito.when(retrieveProduct.getAllProducts()).thenReturn(products);
        List<Product> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}