package smit.teste.crud.controllers;

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
@Tag(name = "Pedidos")
public class PedidoController {
    private final PedidoService pedidoService;
    private final PedidoMapper pedidoMapper;

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody PedidoCreateDTO dto) {
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

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listarTodos() {
        List<Pedido> pedidos = pedidoService.listarTodos();
        List<PedidoResponseDTO> responseList = pedidos.stream()
                .map(pedidoMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable Integer id) {
        return pedidoService.buscarPorId(id)
                .map(pedidoMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (pedidoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        pedidoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
