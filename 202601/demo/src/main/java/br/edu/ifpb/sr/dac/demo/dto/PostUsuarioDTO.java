package br.edu.ifpb.sr.dac.demo.dto;


public record PostUsuarioDTO(
        String nome,
        String username,
        String senha,
        String confirmacaoSenha
) {}
