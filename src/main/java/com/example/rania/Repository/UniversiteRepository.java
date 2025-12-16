package com.example.rania.Repository;

import com.example.rania.Entity.Universite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UniversiteRepository extends JpaRepository<Universite, Long> {
    Optional<Universite> findByNomUniversite(String nomUniversite);}
