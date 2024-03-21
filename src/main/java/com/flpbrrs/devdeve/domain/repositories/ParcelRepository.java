package com.flpbrrs.devdeve.domain.repositories;

import com.flpbrrs.devdeve.domain.models.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ParcelRepository extends JpaRepository<Parcel, UUID> { }
