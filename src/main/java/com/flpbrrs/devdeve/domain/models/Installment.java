package com.flpbrrs.devdeve.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.flpbrrs.devdeve.domain.validations.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
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

    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.ClientID.class)
    @NotNull
    @ManyToOne
    private Client client;

    @NotNull
    @Positive
    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @NotBlank
    @Column(name = "job_description", nullable = false)
    private String jobDescription;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "installment", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = { "installment" ,"hibernateLazyInitializer", "handler" }, allowSetters = true)
    private List<Parcel> parcels;

    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
}
