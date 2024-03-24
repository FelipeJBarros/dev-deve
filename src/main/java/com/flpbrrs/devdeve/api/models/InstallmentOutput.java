package com.flpbrrs.devdeve.api.models;

import com.flpbrrs.devdeve.domain.models.Installment;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstallmentOutput {
    private UUID id;
    private ClientOutput client;
    private String description;
    private BigDecimal amount;
    private List<ParcelOutput> parcels;
    private OffsetDateTime createdAt;

    public static InstallmentOutput toModel(Installment installment) {
        return InstallmentOutput.builder()
                .id(installment.getId())
                .client(ClientOutput.toModel(installment.getClient()))
                .description(installment.getJobDescription())
                .amount(installment.getTotalAmount())
                .parcels(installment.getParcels().stream().map(ParcelOutput::toModel).toList())
                .createdAt(installment.getCreatedAt())
                .build();
    }

    public static List<InstallmentOutput> toCollectionModel(List<Installment> installments) {
        return installments.stream().map(InstallmentOutput::toModel).toList();
    }
}
