package com.springbootapi.dto.response;

import com.springbootapi.entidade.embedded.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PedidoResponseDto(
        Long id,
        LocalDateTime dataPedido,
        BigDecimal valorTotal,
        Status status,
        Long clienteId,
        String nomeCliente
) {
}
