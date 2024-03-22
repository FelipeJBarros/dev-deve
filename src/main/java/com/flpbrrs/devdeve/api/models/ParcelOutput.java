package com.flpbrrs.devdeve.api.models;

import com.flpbrrs.devdeve.domain.models.Parcel;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParcelOutput {
    private UUID id;
    private BigDecimal amount;
    private LocalDate payDate;
    private boolean isPayed;

    public static ParcelOutput toModel(Parcel parcel) {
        return ParcelOutput.builder()
                .id(parcel.getId())
                .amount(parcel.getAmount())
                .payDate(parcel.getPayDate())
                .isPayed(parcel.isPayed())
                .build();
    }
}
