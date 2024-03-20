package com.flpbrrs.devdeve.api.controller;

import com.flpbrrs.devdeve.domain.models.Client;
import com.flpbrrs.devdeve.domain.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
