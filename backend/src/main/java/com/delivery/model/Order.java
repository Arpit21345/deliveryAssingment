package com.delivery.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @Column(name = "order_id")
    private String orderId; // Custom order ID (admin enters it)

    @Column(name = "customer_id")
    private Long customerId;

    private String paymentMode; // "Online" or "COD"

    private Double paymentAmount;

    private String deliveryStatus; // pending, assigned, delivered

    @ManyToOne
    @JoinColumn(name = "delivery_person_id", referencedColumnName = "id", nullable = true)
    private DeliveryPerson deliveryPerson;
}
