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
import smit.teste.crud.api.dtos.produto.ProdutoCreateDTO;
import smit.teste.crud.api.dtos.produto.ProdutoUpdateDTO;
import smit.teste.crud.models.Produto;
import smit.teste.crud.services.ProdutoService;
import smit.teste.crud.utils.ProdutoMapper;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
@Tag(name = "Produtos", description = "Gerenciamento de produtos cadastrados no sistema")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final ProdutoMapper produtoMapper;

    @Operation(
            summary = "Listar produtos",
            description = "Retorna todos os produtos cadastrados no sistema"
    )
    @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        List<Produto> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(produtos);
    }

    @Operation(
            summary = "Buscar produto por ID",
            description = "Retorna os dados de um produto específico pelo seu identificador"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(
            @Parameter(description = "ID numérico do produto") @PathVariable Integer id) {
        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Criar produto",
            description = "Cadastra um novo produto com nome, descrição, preço e quantidade em estoque"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<Produto> criar(
            @Valid @RequestBody ProdutoCreateDTO dto) {
        Produto produto = produtoMapper.toEntity(dto);
        Produto salvo = produtoService.salvar(produto);
        return ResponseEntity
                .created(URI.create("/api/produtos/" + salvo.getId()))
                .body(salvo);
    }

    @Operation(
            summary = "Atualizar parcialmente um produto",
            description = "Atualiza os campos informados de um produto específico"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Produto> atualizarParcialmente(
            @Parameter(description = "ID do produto a ser atualizado") @PathVariable Integer id,
            @Valid @RequestBody ProdutoUpdateDTO dto) {
        return produtoService.buscarPorId(id)
                .map(produto -> {
                    produtoMapper.updateFromDto(dto, produto);
                    Produto atualizado = produtoService.salvar(produto);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Deletar produto",
            description = "Remove um produto do sistema com base em seu ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do produto a ser deletado") @PathVariable Integer id) {
        if (produtoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
