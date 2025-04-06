package com.delivery.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "delivery_person")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contactNumber;

    @Column(nullable = false)
    private String status; // available or busy
}
