package br.edu.ifpb.sr.dac.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostChamadoReqDTO(
        @NotBlank(message = "Título deve ser informado")
        String titulo,
        @NotBlank(message = "Descrição deve ser informada")
        String descricao,
        @NotNull(message = "Prioridade deve ser informada")
        @Min(value = 0, message = "Prioridade deve ser positiva")
        Integer prioridade) {
}
