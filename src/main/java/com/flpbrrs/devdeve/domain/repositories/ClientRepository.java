package com.flpbrrs.devdeve.domain.repositories;

import com.flpbrrs.devdeve.domain.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> { }
