package com.springbootapi.dto.response;

public record ClienteResponseDto(
        String mensagem,
        String status,
        String nome,
        String telefone

) {
}
