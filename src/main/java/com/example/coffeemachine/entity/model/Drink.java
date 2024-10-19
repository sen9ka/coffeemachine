package com.example.coffeemachine.entity.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "drinks")
public class Drink {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_standard")
    private Boolean isStandard;

}
