package com.springbootapi.service;

import com.springbootapi.dto.request.ClienteRequestDto;
import com.springbootapi.dto.response.ClienteResponseDto;
import com.springbootapi.entidade.Cliente;
import com.springbootapi.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponseDto cadastrarCliente(ClienteRequestDto clienteRequestDto){
        if(clienteRequestDto == null){
            return new ClienteResponseDto(
                    "Erro ao cadastrar um cliente",
                    "400",
                    "",
                    ""
            );
        }

        Cliente cliente = new Cliente();

        cliente.setNome(clienteRequestDto.nome());
        cliente.setTelefone(clienteRequestDto.telefone());
        cliente.setEndereco(clienteRequestDto.endereco());
        cliente.setEmail(clienteRequestDto.email());

        clienteRepository.save(cliente);

        return new ClienteResponseDto(
                "Cliente cadastrado com sucesso",
                "201",
                cliente.getNome(),
                cliente.getTelefone()
        );

    }

    public List<Cliente> buscarTodosOsClientes(){
        return clienteRepository.findAll();

    }
}
