package com.springbootapi.dto.request;

import com.springbootapi.entidade.embedded.Status;

import java.math.BigDecimal;

public record PedidoRequestDto(
        BigDecimal valorTotal,
        Status status
) {
}
