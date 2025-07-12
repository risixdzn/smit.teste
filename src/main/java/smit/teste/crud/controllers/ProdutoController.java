package smit.teste.crud.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smit.teste.crud.api.dtos.produto.ProdutoCreateDTO;
import smit.teste.crud.api.dtos.produto.ProdutoUpdateDTO;
import smit.teste.crud.models.Produto;
import smit.teste.crud.services.ProdutoService;
import smit.teste.crud.utils.ProdutoMapper;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
@Tag(name = "Produtos")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final ProdutoMapper produtoMapper;

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        List<Produto> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable UUID id) {
        return produtoService.buscarPorId(id)
                .map(produto -> ResponseEntity.ok(produto))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Produto> criar(@Valid @RequestBody ProdutoCreateDTO dto) {
        Produto produto = produtoMapper.toEntity(dto);
        Produto salvo = produtoService.salvar(produto);
        return ResponseEntity
                .created(URI.create("/api/produtos/" + salvo.getId()))
                .body(salvo);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Produto> atualizarParcialmente(
            @PathVariable UUID id,
            @Valid @RequestBody ProdutoUpdateDTO dto) {

        return produtoService.buscarPorId(id)
                .map(produto -> {
                    produtoMapper.updateFromDto(dto, produto);
                    Produto atualizado = produtoService.salvar(produto);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        if (produtoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
