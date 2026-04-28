package com.springbootapi.service;

import com.springbootapi.dto.request.ClienteRequestDto;
import com.springbootapi.dto.response.ApiResponse;
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

    public ApiResponse<ClienteResponseDto> cadastrarCliente(ClienteRequestDto clienteRequestDto){

        Cliente cliente = new Cliente();

        cliente.setNome(clienteRequestDto.nome());
        cliente.setTelefone(clienteRequestDto.telefone());
        cliente.setEndereco(clienteRequestDto.endereco());
        cliente.setEmail(clienteRequestDto.email());

        clienteRepository.save(cliente);

        ClienteResponseDto dto = new ClienteResponseDto(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getEndereco()
        );

        return new ApiResponse<>(
                "Cliente Cadastrado com sucesso",
                "sucesso",
                dto
        );

    }

    public ApiResponse<List<ClienteResponseDto>> buscarTodosOsClientes(){
        List<ClienteResponseDto> dto = clienteRepository.findAll()
                .stream()
                .map(x -> new ClienteResponseDto(
                        x.getId(),
                        x.getNome(),
                        x.getEmail(),
                        x.getTelefone(),
                        x.getEndereco()
                )).toList();

        return new ApiResponse<>(
                "Consulta realizada com sucesso",
                "Sucesso",
                dto
        );

    }

    public ApiResponse<ClienteResponseDto> editaCliente(int id, ClienteRequestDto clienteRequestDto){
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

        ClienteResponseDto dto = new ClienteResponseDto(
                clienteEditado.getId(),
                clienteEditado.getNome(),
                clienteEditado.getEmail(),
                clienteEditado.getTelefone(),
                clienteEditado.getEndereco()
        );

        return new ApiResponse<>(
                "Cliente editado com sucesso",
                "sucesso",
                dto
        );

    }

    public void deletarUmCliente(int id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        clienteRepository.delete(cliente);
    }
}
