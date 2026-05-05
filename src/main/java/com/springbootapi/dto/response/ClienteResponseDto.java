package com.springbootapi.dto.response;

public record ClienteResponseDto(
        Long id,
        String nome,
        String email,
        String telefone,
        String endereco

) {
}
