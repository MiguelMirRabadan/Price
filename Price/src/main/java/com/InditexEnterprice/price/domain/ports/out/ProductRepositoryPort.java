package com.InditexEnterprice.price.domain.ports.out;

import com.InditexEnterprice.price.domain.models.Product;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepositoryPort {
    @Transactional
    Product save(Product product);
    Optional<Product> findById(Long productId);
    List<Product> findAll();
    @Transactional
    Optional<Product> update(Product product);
    @Transactional
    @Retryable(retryFor = SQLException.class, maxAttempts = 2, backoff = @Backoff(delay = 100))
    /*A pesar de que no es el mejor de los casos donde utilizar retryable, mencionar
        que conozco el pattern de circuit breaker y he trabajdo con algunas de sus cualidades de resilencia.
        Como funciona con una función @Recover para garantizar situaciones más complejas.
    */
    boolean deletedById(Long productId);
}
