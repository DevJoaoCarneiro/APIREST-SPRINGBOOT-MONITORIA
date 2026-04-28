package com.springbootapi.dto.response;

public record ClienteResponseDto(
        Integer id,
        String nome,
        String email,
        String telefone,
        String endereco

) {
}
