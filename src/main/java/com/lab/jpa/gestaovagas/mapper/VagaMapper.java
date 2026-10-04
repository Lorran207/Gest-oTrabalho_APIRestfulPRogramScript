package com.lab.jpa.gestaovagas.mapper;

import com.lab.jpa.gestaovagas.domain.model.Vaga;
import com.lab.jpa.gestaovagas.dto.VagaRequestDTO;
import com.lab.jpa.gestaovagas.dto.VagaResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VagaMapper {

    VagaResponseDTO toDTO(Vaga entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    Vaga toEntity(VagaRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    void updateEntityFromDTO(VagaRequestDTO dto, @MappingTarget Vaga entity);
}
