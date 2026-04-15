package com.springbootapi.controller;

import com.springbootapi.dto.request.ClienteRequestDto;
import com.springbootapi.dto.response.ClienteResponseDto;
import com.springbootapi.entidade.Cliente;
import com.springbootapi.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDto> criarCliente(@RequestBody ClienteRequestDto clienteRequestDto){
        var resposta = clienteService.cadastrarCliente(clienteRequestDto);
       return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping
    public ResponseEntity buscarTodosCliente(){
        var resposta = clienteService.buscarTodosOsClientes();
        return ResponseEntity.ok().body(resposta);
    }
}
