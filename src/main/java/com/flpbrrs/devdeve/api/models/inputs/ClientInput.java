package com.flpbrrs.devdeve.api.models.inputs;

import com.flpbrrs.devdeve.domain.models.Client;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientInput {
    @NotBlank
    @Size(max = 60)
    private String name;

    @Email
    @NotBlank
    @Size(max = 255)
    private String email;

    public static Client toEntity(ClientInput client) {
        return Client.builder()
                .email(client.getEmail())
                .name(client.getName())
                .build();
    }
}
