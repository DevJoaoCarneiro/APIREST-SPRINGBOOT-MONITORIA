package com.springbootapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDto(
        @NotBlank(message = "Nome não pode vir vazio")
        String nome,
        @NotBlank(message = "Telefone não pode vir vazio")
        String telefone,
        @NotBlank(message = "Email não pode vir vazio")
        @Email
        String email,
        String endereco
) {
}
