package br.edu.ifpb.sr.dac.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginReqDTO(
        @NotBlank(message = "Nome de usuário não informado")
        String username,
        @NotBlank(message = "Senha não informada")
        String password) {
}

