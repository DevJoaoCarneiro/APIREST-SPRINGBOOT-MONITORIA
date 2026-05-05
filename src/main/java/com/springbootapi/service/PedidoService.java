package com.springbootapi.service;

import com.springbootapi.dto.request.PedidoRequestDto;
import com.springbootapi.dto.response.ApiResponse;
import com.springbootapi.dto.response.PedidoResponseDto;
import com.springbootapi.entidade.Cliente;
import com.springbootapi.entidade.Pedido;
import com.springbootapi.expections.ResourceNotFoundExpection;
import com.springbootapi.repository.ClienteRepository;
import com.springbootapi.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PedidoService {

    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;

    public PedidoService(ClienteRepository clienteRepository, PedidoRepository pedidoRepository) {
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public ApiResponse<PedidoResponseDto> criarNovoPedido(Long id, PedidoRequestDto pedidoRequestDto) {
            Cliente cliente = clienteRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundExpection("Cliente não encontrado"));

            Pedido pedido = new Pedido();

            pedido.setDataPedido(LocalDateTime.now());
            pedido.setValorTotal(pedidoRequestDto.valorTotal());
            pedido.setStatus(pedidoRequestDto.status());
            pedido.setCliente(cliente);

            pedidoRepository.save(pedido);

            PedidoResponseDto dto = new PedidoResponseDto(
                    pedido.getId(),
                    pedido.getDataPedido(),
                    pedido.getValorTotal(),
                    pedido.getStatus(),
                    cliente.getId(),
                    cliente.getNome()
            );

            return new ApiResponse<>(
                    "Pedido cadastrado com sucesso",
                    "sucesso",
                    dto
            );

    }
}
