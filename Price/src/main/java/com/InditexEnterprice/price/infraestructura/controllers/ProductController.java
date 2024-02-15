package com.InditexEnterprice.price.infraestructura.controllers;

import com.InditexEnterprice.price.appplication.services.ProductService;
import com.InditexEnterprice.price.domain.models.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@Log4j2
public class ProductController {

    @Autowired
    private final ProductService productService;

    @PostMapping
    @Operation(summary = "Create new Product", description = "This function recieve the json of a new product to create it")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Prodcut created correctly"),
            @ApiResponse(responseCode = "400", description = "Error: Could not create any price")
    })
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product createdProduct = productService.createProduct(product);
        log.info("Created a new Product: "+ createdProduct);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/getAllProducts")
    @Operation(summary = "Get all Products", description = "This function return all products that currently exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Products found"),
            @ApiResponse(responseCode = "400", description = "Error: Could not found any product")
    })
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
