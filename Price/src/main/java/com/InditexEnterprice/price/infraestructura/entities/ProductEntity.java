package com.InditexEnterprice.price.infraestructura.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Product")
@Data
public class ProductEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", unique = true)
    private Long productId;
    @Column(name = "description" , nullable = true, length = 255)
    private String description;

}
