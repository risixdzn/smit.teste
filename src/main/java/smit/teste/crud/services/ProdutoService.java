package smit.teste.crud.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smit.teste.crud.models.Produto;
import smit.teste.crud.repositories.ProdutoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(UUID id) {
        return produtoRepository.findById(id);
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deletar(UUID id) {
        produtoRepository.deleteById(id);
    }
}