package com.example.coffeemachine.entity.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "statistics")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Statistic {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "drink_id", nullable = false)
    private Drink drink;

    @Column(name = "order_date")
    private LocalDate orderDate;

}
