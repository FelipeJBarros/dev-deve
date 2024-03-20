package com.flpbrrs.devdeve.domain.services;

import com.flpbrrs.devdeve.domain.exceptions.DomainException;
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
        boolean emailAlreadyExists = clientRepository
                .findByEmail(client.getEmail())
                .filter(c -> !c.equals(client))
                .isPresent();
        if (emailAlreadyExists) throw new DomainException("This email is already in use");
        return clientRepository.save(client);
    }

    @Transactional
    public void delete(UUID id) {
        clientRepository.deleteById(id);
    }
}
