package com.springbootapi.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PedidoResponseDto(
        Long id,
        LocalDateTime dataPedido,
        BigDecimal valorTotal,
        String status,
        Long clienteId,
        String nomeCliente
) {
}
