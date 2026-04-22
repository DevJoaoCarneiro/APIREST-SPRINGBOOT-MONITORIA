package com.springbootapi.service;

import com.springbootapi.dto.request.ClienteRequestDto;
import com.springbootapi.dto.response.ClienteResponseDto;
import com.springbootapi.entidade.Cliente;
import com.springbootapi.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponseDto cadastrarCliente(ClienteRequestDto clienteRequestDto){

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

    public ClienteResponseDto editaCliente(int id, ClienteRequestDto clienteRequestDto){
        Cliente clienteEditado = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Erro ao encontrar cliente"));

        if(clienteRequestDto.nome() != null){
            clienteEditado.setNome(clienteRequestDto.nome());
        }

        if(clienteRequestDto.email() != null){
            clienteEditado.setEmail(clienteRequestDto.email());
        }

        if(clienteRequestDto.endereco() != null){
            clienteEditado.setEndereco(clienteRequestDto.endereco());
        }

        if(clienteRequestDto.telefone() != null){
            clienteEditado.setTelefone(clienteRequestDto.telefone());
        }

        clienteRepository.save(clienteEditado);

        return new ClienteResponseDto(
                "Cliente atualizado com sucesso",
                "Updated Client",
                clienteEditado.getNome(),
                clienteEditado.getTelefone()
        );

    }
}
