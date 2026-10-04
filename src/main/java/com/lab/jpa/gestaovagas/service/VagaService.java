package com.lab.jpa.gestaovagas.service;

import com.lab.jpa.gestaovagas.domain.model.Vaga;
import com.lab.jpa.gestaovagas.dto.VagaRequestDTO;
import com.lab.jpa.gestaovagas.dto.VagaResponseDTO;
import com.lab.jpa.gestaovagas.exception.ResourceNotFoundException;
import com.lab.jpa.gestaovagas.mapper.VagaMapper;
import com.lab.jpa.gestaovagas.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VagaService {

    private final VagaRepository repository;
    private final VagaMapper mapper;

    @Transactional(readOnly = true)
    public Page<VagaResponseDTO> listarTodas(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    @Transactional
    public VagaResponseDTO salvar(VagaRequestDTO dto) {
        Vaga vaga = mapper.toEntity(dto);
        Vaga saved = repository.save(vaga);
        return mapper.toDTO(saved);
    }

    @Transactional(readOnly = true)
    public VagaResponseDTO buscarPorId(UUID id) {
        return mapper.toDTO(buscarEntidade(id));
    }

    @Transactional
    public VagaResponseDTO atualizar(UUID id, VagaRequestDTO dto) {
        Vaga vaga = buscarEntidade(id);
        mapper.updateEntityFromDTO(dto, vaga);
        Vaga updated = repository.save(vaga);
        return mapper.toDTO(updated);
    }

    @Transactional
    public void remover(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Vaga não encontrada com o ID: " + id);
        }
        repository.deleteById(id);
    }

    private Vaga buscarEntidade(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vaga não encontrada com o ID: " + id));
    }
}
