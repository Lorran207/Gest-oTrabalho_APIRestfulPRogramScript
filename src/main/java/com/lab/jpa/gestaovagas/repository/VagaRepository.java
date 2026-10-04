package com.lab.jpa.gestaovagas.repository;

import com.lab.jpa.gestaovagas.domain.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VagaRepository extends JpaRepository<Vaga, UUID> {
}
