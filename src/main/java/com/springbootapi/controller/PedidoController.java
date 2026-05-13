package com.springbootapi.controller;

import com.springbootapi.dto.request.PedidoRequestDto;
import com.springbootapi.dto.response.ApiResponse;
import com.springbootapi.dto.response.PedidoResponseDto;
import com.springbootapi.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PatchExchange;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<ApiResponse<List<PedidoResponseDto>>> buscarTodosOsPedidos(){
        var response = pedidoService.consultaTodosOsPedidos();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PedidoResponseDto>> buscarPedidoPorId(@PathVariable Long id){
        var response = pedidoService.consultaPedidoPorId(id);
        return ResponseEntity.ok().body(response);
    }

    @PatchExchange("/{pedidoId}/status/{statusId}")
    public ResponseEntity<ApiResponse<PedidoResponseDto>> editarStatusDoPedidoPorId(
            @PathVariable Long pedidoId,
            @PathVariable Long statusId
    ){
        var response = pedidoService.editarStatusPedido(pedidoId, statusId);

        return ResponseEntity.ok().body(response);
    }
}
