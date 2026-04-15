package com.springbootapi.controller;

import com.springbootapi.dto.request.ClienteRequestDto;
import com.springbootapi.entidade.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @PostMapping
    public ResponseEntity criarCliente(@RequestBody ClienteRequestDto clienteRequestDto){
        System.out.println("Deu certo");
        System.out.println("Nome......"+clienteRequestDto.nome());
        System.out.println("Telefone.."+clienteRequestDto.telefone());
        System.out.println("Email....."+clienteRequestDto.email());
        System.out.println("Endereco.."+clienteRequestDto.endereco());
       return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
