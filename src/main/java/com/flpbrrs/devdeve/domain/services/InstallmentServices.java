package com.flpbrrs.devdeve.domain.services;

import com.flpbrrs.devdeve.domain.exceptions.DomainException;
import com.flpbrrs.devdeve.domain.models.Client;
import com.flpbrrs.devdeve.domain.models.Installment;
import com.flpbrrs.devdeve.domain.repositories.InstallmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class InstallmentServices {
    private final InstallmentRepository installmentRepository;
    private final ClientServices clientServices;

    @Transactional
    public Installment save(Installment installment) {
        if (installment.getId() != null) {
            throw new DomainException("installments to be created must not have an id");
        }

        Client client = clientServices.find(installment.getClient().getId());

        installment.setClient(client);
        installment.setParcels(new ArrayList<>());

        return installmentRepository.save(installment);
    }
}
