package br.edu.ifpb.sr.dac.demo.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostUsuarioDTO(
        @NotBlank(message = "Nome deve ser informado")
        String nome,
        @NotBlank(message = "Username deve ser informado")
        String username,
        @NotBlank(message = "Senha deve ser informada")
        String senha,
        @NotBlank(message = "Confirmação deve ser informada")
        String confirmacaoSenha,
        @NotBlank(message = "Email deve ser informado")
        @Email(message = "Formato de email inválido!")
        String email,
        @NotBlank(message = "Cpf deve ser informado")
        String cpf
) {}
