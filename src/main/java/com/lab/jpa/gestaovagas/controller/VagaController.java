package com.lab.jpa.gestaovagas.controller;

import com.lab.jpa.gestaovagas.dto.VagaRequestDTO;
import com.lab.jpa.gestaovagas.dto.VagaResponseDTO;
import com.lab.jpa.gestaovagas.service.VagaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/vagas")
@RequiredArgsConstructor
@Tag(name = "Vagas", description = "API RESTful para Gestão de Vagas de Emprego")
public class VagaController {

    private final VagaService service;

    @PostMapping
    @Operation(summary = "Criar nova vaga", description = "Cadastra uma oportunidade de vaga de emprego")
    public ResponseEntity<VagaResponseDTO> criar(@Valid @RequestBody VagaRequestDTO dto) {
        VagaResponseDTO vagaSalva = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(vagaSalva);
    }

    @GetMapping
    @Operation(summary = "Listar todas as vagas com paginação",
            description = "Retorna uma página de vagas registradas com ordenação padrão por título asc.")
    public ResponseEntity<Page<VagaResponseDTO>> listar(
            @PageableDefault(page = 0, size = 10, sort = "titulo", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(service.listarTodas(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter vaga por ID", description = "Recupera os detalhes de uma vaga específica informando o UUID.")
    public ResponseEntity<VagaResponseDTO> obter(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar vaga", description = "Atualiza completamente as informações de uma vaga existente.")
    public ResponseEntity<VagaResponseDTO> atualizar(@PathVariable UUID id,
                                                     @Valid @RequestBody VagaRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar vaga", description = "Remove o registro de uma vaga do sistema.")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
