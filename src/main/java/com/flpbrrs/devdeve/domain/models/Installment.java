package com.flpbrrs.devdeve.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "installments")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Installment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @ManyToOne
    private Client client;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "job_description", nullable = false)
    private String jobDescription;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "installment", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = { "installment" ,"hibernateLazyInitializer", "handler" }, allowSetters = true)
    private List<Parcel> parcels;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
