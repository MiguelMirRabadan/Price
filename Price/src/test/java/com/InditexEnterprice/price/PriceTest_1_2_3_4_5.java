package com.InditexEnterprice.price;

import com.InditexEnterprice.price.domain.models.Price;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@Log4j2
class PriceTest_1_2_3_4_5 {
    private String apiUrl;

    private String json = "{\"brandId\":\"1\",\"startDate\":\"2023-05-14T10:00:00\"," +
        "\"endDate\":\"2023-05-25T11:02:26.977Z\",\"priceList\":\"0\",\"productId\":\"35455\"," +
        "\"priority\":\"0\",\"price\":\"0\",\"currency\":\"EUR\"}";

    private final Long PRICE_ID=5L;
    private final Long BRAND_ID = 1L;
    private final Long PRODUCT_ID = 35455L;

    @BeforeEach
    void setUp(){
        log.info("PrepareNothing");
    }

    @Test
    void createPrice() {
        log.info("createPrice");
        apiUrl = "http://localhost:8080/api/price";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(json,httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Price> response = restTemplate.postForEntity(apiUrl,requestEntity, Price.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    private static Stream<Arguments> peticiones(){
        return Stream.of(
                Arguments.of(LocalDateTime.of(2023,05,14,10,00,00)),
                Arguments.of(LocalDateTime.of(2023,05,14,16,00,00)),
                Arguments.of(LocalDateTime.of(2023,05,14,21,00,00)),
                Arguments.of(LocalDateTime.of(2023,05,15,10,00,00)),
                Arguments.of(LocalDateTime.of(2023,05,16,21,00,00))
        );
    }

    @ParameterizedTest
    @MethodSource("peticiones")
    void createPriceByParams(LocalDateTime date) {
        log.info("createPriceByParams");
        apiUrl = "http://localhost:8080/api/price";
        long brandId = 1L;
        long productId = 35455L;
        String apiUrlWithParams = apiUrl + "/"+date+"/"+brandId+"/"+productId;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Price> response = restTemplate.postForEntity(apiUrlWithParams,null, Price.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
    }


    @Test
    void getPriceById() {
        log.info("getPriceById");
        apiUrl = "http://localhost:8080/api/price";
        Long priceId = 1L;
        String apiUrlWithParams = apiUrl + "/"+priceId;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Price> response = restTemplate.getForEntity(apiUrlWithParams,Price.class);

        assertTrue(response.getStatusCode().is3xxRedirection());
    }

    @Test
    void getAllPrices() {
        log.info("getAllPrices");
        apiUrl = "http://localhost:8080/api/price/getAllPrices";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Price>> response = restTemplate.exchange(apiUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Price>>() {}
        );

        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    void updatePrice() {
        log.info("updatePrice");
        apiUrl = "http://localhost:8080/api/price";
        Long priceId = 5L;
        String apiUrlWithParams = apiUrl + "/"+priceId;

        json = "{\"brandId\":\"1\",\"startDate\":\"2023-05-14T10:00:00\"," +
                "\"endDate\":\"2023-05-25T11:02:26.977Z\",\"priceList\":\"0\",\"productId\":\"35455\"," +
                "\"priority\":\"0\",\"price\":\"0\",\"currency\":\"UPDATED\"}";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(json,httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Price> response = restTemplate.exchange(apiUrlWithParams,HttpMethod.PUT,requestEntity
                ,Price.class);


        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    void deletePrice() {
        log.info("deletePrice");
        apiUrl = "http://localhost:8080/api/price";
        Long priceId = 6L;
        String apiUrlWithParams = apiUrl + "/"+priceId;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(apiUrlWithParams,HttpMethod.DELETE,null
                ,Void.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
}