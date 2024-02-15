package com.InditexEnterprice.price.infraestructura.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@Table(name = "Price")
@Log4j2     
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long priceId;

    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = true)
    private LocalDateTime endDate;

    @Column(name = "price_list")
    private Long priceList;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "product_id")
    private ProductEntity product;

    @Column(name = "priority")
    private Long priority;

    @Column(name = "price")
    private float price;

    @Column(name = "curr")
    private String currency;

    @PrePersist
    protected void onCreate(){
        log.trace("Preparing data of Price Entity");
        brandId = (getBrandId()<0)? 0L : getBrandId();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
        startDate = (getStartDate() == null) ? LocalDateTime.parse(
                LocalDateTime.now().withNano(0).format(formatter), formatter)
                : LocalDateTime.parse(getStartDate().format(formatter), formatter);
        endDate = (getEndDate() == null) ? LocalDateTime.parse(
                LocalDateTime.now().withNano(0).format(formatter), formatter)
                : LocalDateTime.parse(getEndDate().format(formatter), formatter);;
        priority = (getPriority() == null) ? 0L : getPriority();
        price = (getPrice()<0)? 0L : getPrice();
        currency = (getCurrency()==null)? "EUR" : getCurrency();
    }
}
