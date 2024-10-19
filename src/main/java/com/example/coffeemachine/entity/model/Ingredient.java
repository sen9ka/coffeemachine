package com.example.coffeemachine.entity.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Integer quantity;

}
