package com.springbootapi.controller;

import com.springbootapi.dto.request.ClienteRequestDto;
import com.springbootapi.entidade.Cliente;
import com.springbootapi.service.ClienteService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody ClienteRequestDto clienteRequestDto){
       clienteService.criarNovoCliente(clienteRequestDto);
       return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
