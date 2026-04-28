package com.springbootapi.dto.response;

public record ApiResponse<T>(
        String mensagem,
        String status,
        T data
) {
}
