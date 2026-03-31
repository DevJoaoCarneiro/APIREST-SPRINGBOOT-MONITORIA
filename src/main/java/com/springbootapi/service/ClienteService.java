package com.springbootapi.service;

import com.springbootapi.IClienteRepository;
import com.springbootapi.dto.request.ClienteRequestDto;
import com.springbootapi.entidade.Cliente;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final IClienteRepository clienteRepository;

    public ClienteService(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void criarNovoCliente(ClienteRequestDto clienteRequestDto){

        Cliente cliente = new Cliente();
        cliente.setNome(clienteRequestDto.getNome());
        cliente.setTelefone(cliente.getTelefone());
        cliente.setEmail(cliente.getEmail());

        clienteRepository.save(cliente);

    }
}
