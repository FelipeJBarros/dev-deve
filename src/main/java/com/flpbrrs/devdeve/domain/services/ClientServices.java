package com.flpbrrs.devdeve.domain.services;

import com.flpbrrs.devdeve.domain.models.Client;
import com.flpbrrs.devdeve.domain.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientServices {
    private final ClientRepository clientRepository;

    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Transactional
    public void delete(UUID id) {
        clientRepository.deleteById(id);
    }
}
