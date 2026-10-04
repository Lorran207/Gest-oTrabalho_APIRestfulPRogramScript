package com.lab.jpa.gestaovagas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record VagaRequestDTO(
        @NotBlank(message = "O título é obrigatório.")
        @Size(max = 100, message = "O título deve ter no máximo 100 caracteres.")
        String titulo,

        String descricao,

        @NotNull(message = "O salário é obrigatório.")
        @Positive(message = "O salário deve ser um valor positivo.")
        Double salario
) {
}
