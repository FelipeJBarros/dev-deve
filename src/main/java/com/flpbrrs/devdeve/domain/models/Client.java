package com.flpbrrs.devdeve.domain.models;

import com.flpbrrs.devdeve.domain.validations.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="clients")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    @NotNull(groups = ValidationGroups.ClientID.class)
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank
    @Size(max = 60)
    @Column(nullable = false)
    private String name;

    @Email
    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, unique = true)
    private String email;
}
