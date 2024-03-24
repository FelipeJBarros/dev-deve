package com.flpbrrs.devdeve.api.controllers;

import com.flpbrrs.devdeve.api.models.ClientOutput;
import com.flpbrrs.devdeve.api.models.inputs.ClientInput;
import com.flpbrrs.devdeve.domain.models.Client;
import com.flpbrrs.devdeve.domain.repositories.ClientRepository;
import com.flpbrrs.devdeve.domain.services.ClientServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("clients")
@AllArgsConstructor
public class ClientController {
    private final ClientRepository clientRepository;
    private final ClientServices clientServices;
    @GetMapping
    public List<ClientOutput> list() {
        List<Client> clients = clientRepository.findAll();
        return ClientOutput.toCollectionModel(clients);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable UUID id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientOutput create(@Valid @RequestBody ClientInput data) {
        Client newClient = clientServices.save(ClientInput.toEntity(data));
        return ClientOutput.toModel(newClient);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClientOutput> update(@PathVariable UUID id, @Valid @RequestBody ClientInput clientData) {
        boolean userExists = clientRepository.existsById(id);
        if (!userExists) return ResponseEntity.notFound().build();

        Client client = ClientInput.toEntity(clientData);
        client.setId(id);
        client = clientServices.save(client);

        return ResponseEntity.ok(ClientOutput.toModel(client));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        boolean userExists = clientRepository.existsById(id);
        if (!userExists) return ResponseEntity.notFound().build();
        clientServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
