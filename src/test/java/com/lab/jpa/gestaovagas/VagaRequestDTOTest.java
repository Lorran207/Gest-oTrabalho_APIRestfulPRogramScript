package com.lab.jpa.gestaovagas;

import com.lab.jpa.gestaovagas.dto.VagaRequestDTO;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Testes de validação do DTO (não precisam de banco de dados). */
class VagaRequestDTOTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void dtoValidoNaoGeraViolacoes() {
        var dto = new VagaRequestDTO("Dev Java", "Spring Boot", 5000.0);
        assertTrue(validator.validate(dto).isEmpty());
    }

    @Test
    void dtoInvalidoGeraViolacoes() {
        var dto = new VagaRequestDTO("", null, -500.0);
        assertEquals(2, validator.validate(dto).size());
    }
}
