package com.flpbrrs.devdeve.api.controller;

import com.flpbrrs.devdeve.domain.models.Installment;
import com.flpbrrs.devdeve.domain.repositories.InstallmentRepository;
import com.flpbrrs.devdeve.domain.services.InstallmentServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("installments")
@AllArgsConstructor
public class InstallmentController {
    private final InstallmentRepository installmentRepository;
    private final InstallmentServices installmentServices;
    @GetMapping
    public List<Installment> list() {
        return installmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Installment> getById(@PathVariable UUID id) {
        return installmentRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Installment create(@Valid @RequestBody Installment installment) {
        return installmentServices.save(installment);
    }
}
