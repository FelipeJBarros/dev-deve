package com.flpbrrs.devdeve.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "parcels")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @ManyToOne
    @JsonIgnoreProperties(value = { "parcels" ,"hibernateLazyInitializer", "handler" }, allowSetters = true)
    private Installment installment;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name = "pay_date", nullable = false)
    private LocalDate payDate;

    @Column(name = "is_payed", nullable = false)
    private boolean isPayed;
}
