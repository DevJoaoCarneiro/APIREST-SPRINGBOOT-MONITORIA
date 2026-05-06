package com.springbootapi.dto.request;

import java.math.BigDecimal;

public record PedidoRequestDto(
        BigDecimal valorTotal,
        Long statusId
) {
}
