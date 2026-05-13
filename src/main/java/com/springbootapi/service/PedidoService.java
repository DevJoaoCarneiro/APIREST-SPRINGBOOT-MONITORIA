package com.springbootapi.service;

import com.springbootapi.dto.request.PedidoRequestDto;
import com.springbootapi.dto.response.ApiResponse;
import com.springbootapi.dto.response.PedidoResponseDto;
import com.springbootapi.entidade.Cliente;
import com.springbootapi.entidade.Pedido;
import com.springbootapi.entidade.Status;
import com.springbootapi.expections.ResourceNotFoundExpection;
import com.springbootapi.repository.ClienteRepository;
import com.springbootapi.repository.PedidoRepository;
import com.springbootapi.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;
    private final StatusRepository statusRepository;


    public PedidoService(ClienteRepository clienteRepository, PedidoRepository pedidoRepository, StatusRepository statusRepository) {
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
        this.statusRepository = statusRepository;
    }

    public ApiResponse<PedidoResponseDto> criarNovoPedido(Long id, PedidoRequestDto pedidoRequestDto) {
            Cliente cliente = clienteRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundExpection("Cliente não encontrado"));

            Status status = statusRepository.findById(pedidoRequestDto.statusId())
                    .orElseThrow(() -> new ResourceNotFoundExpection("Status não encontado"));



            Pedido pedido = new Pedido();

            pedido.setDataPedido(LocalDateTime.now());
            pedido.setValorTotal(pedidoRequestDto.valorTotal());
            pedido.setStatus(status);
            pedido.setCliente(cliente);

            pedidoRepository.save(pedido);

            PedidoResponseDto dto = new PedidoResponseDto(
                    pedido.getId(),
                    pedido.getDataPedido(),
                    pedido.getValorTotal(),
                    pedido.getStatus().getNome(),
                    cliente.getId(),
                    cliente.getNome()
            );

            return new ApiResponse<>(
                    "Pedido cadastrado com sucesso",
                    "sucesso",
                    dto
            );

    }

    public ApiResponse<List<PedidoResponseDto>> consultaTodosOsPedidos() {
        List<PedidoResponseDto> dto = pedidoRepository.findAll()
                .stream()
                .map(x -> new PedidoResponseDto(
                        x.getId(),
                        x.getDataPedido(),
                        x.getValorTotal(),
                        x.getStatus().getNome(),
                        x.getCliente().getId(),
                        x.getCliente().getNome()
                )).toList();

        return new ApiResponse<>(
                "Consulta realizada com sucesso",
                "sucesso",
                dto
        );

    }

    public ApiResponse<PedidoResponseDto> consultaPedidoPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExpection("Pedido não encontrado"));

        PedidoResponseDto dto = new PedidoResponseDto(
                pedido.getId(),
                pedido.getDataPedido(),
                pedido.getValorTotal(),
                pedido.getStatus().getNome(),
                pedido.getCliente().getId(),
                pedido.getCliente().getNome()
        );

        return new ApiResponse<>(
                "Pedido consultado com sucesso",
                "sucesso",
                dto
        );
    }

    public ApiResponse<PedidoResponseDto> editarStatusPedido(Long pedidoId, Long statusId) {

        //ATUALIZAR O STATUS DO PEDIDO
        //ACHAR O PEDIDO
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new ResourceNotFoundExpection("Pedido nao encontrado"));

        //VERIFICO SE O STATUS QUE O AMIGAO PASSOU É VALIDO
        Status status = statusRepository.findById(statusId)
                .orElseThrow(() -> new ResourceNotFoundExpection("Status do pedido nao encontrado"));


        //ATUALIZO O REGISTRO QUE BUSQUEI ANTERIORMENTE
        pedido.setStatus(status);

        //Salvar o novo status
        pedidoRepository.save(pedido);

        //Convertendo a entidade para RESPONSE (DTO)
        PedidoResponseDto dto = new PedidoResponseDto(
                pedido.getId(),
                pedido.getDataPedido(),
                pedido.getValorTotal(),
                pedido.getStatus().getNome(),
                pedido.getCliente().getId(),
                pedido.getCliente().getNome()

        );

        //DEVOLVO A MENSAGEM DE SUCESSO
        return new ApiResponse<>(
                "Status do pedido atualizado com sucesso",
                "sucesso",
                dto
        );
    }
}
