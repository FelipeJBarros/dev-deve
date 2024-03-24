package com.flpbrrs.devdeve.api.controllers;

import com.flpbrrs.devdeve.api.models.InstallmentOutput;
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
    public List<InstallmentOutput> list() {
        return InstallmentOutput.toCollectionModel(installmentRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstallmentOutput> getById(@PathVariable UUID id) {
        return installmentRepository
                .findById(id)
                .map(InstallmentOutput::toModel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InstallmentOutput create(@Valid @RequestBody Installment installment) {
        return InstallmentOutput.toModel(installmentServices.save(installment));
    }
}
