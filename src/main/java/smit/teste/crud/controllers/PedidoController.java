package smit.teste.crud.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smit.teste.crud.api.dtos.pedido.PedidoCreateDTO;
import smit.teste.crud.api.dtos.pedido.PedidoResponseDTO;
import smit.teste.crud.models.Pedido;
import smit.teste.crud.services.PedidoService;
import smit.teste.crud.utils.PedidoMapper;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
@Tag(name = "Pedidos", description = "Operações relacionadas a pedidos e suas validações de estoque")
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoMapper pedidoMapper;

    @Operation(
            summary = "Criar um novo pedido",
            description = """
                    Cria um pedido com base em uma lista de itens (produto + quantidade). 
                    Valida se há estoque suficiente para cada item solicitado. 
                    Em caso afirmativo, o estoque dos produtos será reduzido de acordo com as quantidades.
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Estoque insuficiente para um ou mais produtos"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<?> criar(
            @Valid @RequestBody PedidoCreateDTO dto) {
        try {
            Pedido novo = pedidoService.criarPedido(dto);
            PedidoResponseDTO response = pedidoMapper.toDTO(novo);
            return ResponseEntity
                    .created(URI.create("/api/pedidos/" + novo.getId()))
                    .body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @Operation(summary = "Listar todos os pedidos")
    @ApiResponse(responseCode = "200", description = "Lista de pedidos retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listarTodos() {
        List<Pedido> pedidos = pedidoService.listarTodos();
        List<PedidoResponseDTO> responseList = pedidos.stream()
                .map(pedidoMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    @Operation(summary = "Buscar um pedido por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(
            @Parameter(description = "ID do pedido") @PathVariable Integer id) {
        return pedidoService.buscarPorId(id)
                .map(pedidoMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar um pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Pedido deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do pedido") @PathVariable Integer id) {
        if (pedidoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        pedidoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
