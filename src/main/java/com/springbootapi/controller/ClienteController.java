package com.springbootapi.controller;

import com.springbootapi.dto.request.ClienteRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @PostMapping
    public String criarCliente(@RequestBody ClienteRequestDto clienteRequestDto){
        return "Nome..."+clienteRequestDto.getNome()+" Email..."+clienteRequestDto.getEmail();
    }
}
