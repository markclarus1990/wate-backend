package com.msys.water_station.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "inventory_movements")
public class InventoryMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Who created this movement (cashier/admin)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    // Which inventory item
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_item_id", nullable = false)
    private InventoryItem inventoryItem;

    // Movement type (SALE, RESTOCK, ADJUSTMENT)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movement_type_id", nullable = false)
    private MovementType movementType;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal quantity;

    // What caused this movement (SALE, MANUAL_ADJUSTMENT, etc)
    @Column(name = "reference_type_id", nullable = false)
    private Long referenceTypeId;

    @Column(name = "reference_id", nullable = false)
    private Long referenceId;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}