package com.flpbrrs.devdeve.api.controller;

import com.flpbrrs.devdeve.domain.model.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("clients")
public class ClientController {
    @GetMapping
    public List<Client> list() {
        Client cl1 = Client.builder()
                .id(UUID.randomUUID())
                .name("Felipe")
                .email("felipe.email@email.com").build();
        Client cl2 = Client.builder()
                .id(UUID.randomUUID())
                .name("Rafaela")
                .email("rafa.email@email.com").build();

        return Arrays.asList(cl1, cl2);
    }
}
