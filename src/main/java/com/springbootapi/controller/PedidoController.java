package com.springbootapi.controller;

import com.springbootapi.dto.request.PedidoRequestDto;
import com.springbootapi.dto.response.ApiResponse;
import com.springbootapi.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse> criarPedido(@PathVariable Long id, @RequestBody PedidoRequestDto pedidoRequestDto){
        var response = pedidoService.criarNovoPedido(id, pedidoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
