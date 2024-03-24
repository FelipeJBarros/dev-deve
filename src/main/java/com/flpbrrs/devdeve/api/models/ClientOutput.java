package com.flpbrrs.devdeve.api.models;

import com.flpbrrs.devdeve.domain.models.Client;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientOutput {
    private UUID id;
    private String name;

    public static ClientOutput toModel(Client client) {
        return ClientOutput.builder()
                .id(client.getId())
                .name(client.getName())
                .build();
    }

    public static List<ClientOutput> toCollectionModel(List<Client> clients) {
        return clients.stream().map(ClientOutput::toModel).toList();
    }
}
