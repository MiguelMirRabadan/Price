package com.InditexEnterprice.price.infraestructura.controllers;

import com.InditexEnterprice.price.appplication.services.PriceService;
import com.InditexEnterprice.price.domain.models.Price;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/price")
@AllArgsConstructor
@Log4j2
public class PriceController {

    @Autowired
    private final PriceService priceService;

    @PostMapping
    @Operation(summary = "Create new price", description = "This function recieve the json of a new price to create it")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Price created correctly"),
            @ApiResponse(responseCode = "400", description = "Error: Could not create any price")
    })
    public ResponseEntity<Price> createPrice(@Parameter(description = "Json Price") @RequestBody Price price){
        log.debug("CreatePrice method called: "+price );
        Price createdPrice = priceService.createPrice(price);
        return new ResponseEntity<>(createdPrice, HttpStatus.CREATED);
    }

    @PostMapping("/{date}/{brandId}/{productId}")
    @Operation(summary = "Create new price", description = "This function recieve the json of a new price to create it")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Price created correctly"),
            @ApiResponse(responseCode = "400", description = "Error: Could not create any price")
    })
    public ResponseEntity<Price> createPrice(@PathVariable("brandId") Long brandId,
                                             @PathVariable("productId") Long productId,
                                             @PathVariable("date")
                                                 @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss") LocalDateTime date){
        log.debug("CreatePrice method called with params: brandid - "+brandId + " productid - "+productId+ " date - "+date );
        Price createdPrice = priceService.createPrice(brandId, productId, date.withNano(0));
        return new ResponseEntity<>(createdPrice, HttpStatus.CREATED);
    }

    @GetMapping("/{priceId}")
    @Operation(summary = "Get Price by id", description = "This function return one Price if found it by his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Price found"),
            @ApiResponse(responseCode = "404", description = "Error: This price doesn't exist")
    })
    public ResponseEntity<Price> getPriceById(@PathVariable Long priceId){

        return priceService.getPrice(priceId)
                .map(price -> new ResponseEntity<>(price, HttpStatus.FOUND))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/getAllPrices")
    @Operation(summary = "Get all Prices", description = "This function return all Prices that currently exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Prices found"),
            @ApiResponse(responseCode = "404", description = "Error: Could not create any price")
    })
    public ResponseEntity<List<Price>> getAllPrices(){
        List<Price> prices = priceService.getAllPrices();
        return new ResponseEntity<>(prices, HttpStatus.OK);
    }

    @PutMapping("/{priceId}")
    @Operation(summary = "Update a Price", description = "This function update a price searching by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Price updated correctly"),
            @ApiResponse(responseCode = "404", description = "Error: Could not update any price")
    })
    public ResponseEntity<Price> updatePrice(@PathVariable Long priceId, @RequestBody Price updatedPrice){
        return priceService.updatePrice(priceId,updatedPrice)
                .map(price -> new ResponseEntity<>(price , HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{priceId}")
    @Operation(summary = "Delete a price", description = "This function delete a price by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Price deleted correctly"),
            @ApiResponse(responseCode = "404", description = "Error: Could not delete any price")
    })
    public ResponseEntity<Void> deletePrice(@PathVariable Long priceId){
        if(priceService.deletePrice(priceId)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
