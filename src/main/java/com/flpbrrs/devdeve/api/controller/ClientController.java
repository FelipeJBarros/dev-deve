package com.flpbrrs.devdeve.api.controller;

import com.flpbrrs.devdeve.domain.models.Client;
import com.flpbrrs.devdeve.domain.repositories.ClientRepository;
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
    @GetMapping
    public List<Client> list() {
        return clientRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable UUID id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody Client data) {
        return clientRepository.save(data);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable UUID id, @RequestBody Client client) {
        boolean userExists = clientRepository.existsById(id);
        if (!userExists) return ResponseEntity.notFound().build();

        client.setId(id);
        client = clientRepository.save(client);

        return ResponseEntity.ok(client);
    }
}
