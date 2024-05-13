package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "product")
@Entity(name = "product")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String product_name;
    private float price;
    private int stock;

    public Products(RequestProduct requestProduct){
        this.product_name = requestProduct.product_name();
        this.price = requestProduct.price();
        this.stock = requestProduct.stock();
    }
}
