package com.springbootapi.controller;

import com.springbootapi.dto.request.ClienteRequestDto;
import com.springbootapi.dto.response.ApiResponse;
import com.springbootapi.dto.response.ClienteResponseDto;
import com.springbootapi.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ClienteResponseDto>> criarCliente(@Valid @RequestBody ClienteRequestDto clienteRequestDto){
        var resposta = clienteService.cadastrarCliente(clienteRequestDto);
       return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClienteResponseDto>>> buscarTodosCliente(){
        var resposta = clienteService.buscarTodosOsClientes();
        return ResponseEntity.ok().body(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteResponseDto>> buscaClientePorId(@PathVariable Long id){
        var response = clienteService.consultaClientePorId(id);
        return ResponseEntity.ok().body(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteResponseDto>> editaClientePorId(@PathVariable Long id, @RequestBody ClienteRequestDto clienteRequestDto){
        var response = clienteService.editaCliente(id, clienteRequestDto);
        return ResponseEntity.ok().body(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaClientePorId(@PathVariable Long id){
        clienteService.deletarUmCliente(id);
        return ResponseEntity.noContent().build();
    }

}
