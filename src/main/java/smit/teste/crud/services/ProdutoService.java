package smit.teste.crud.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smit.teste.crud.models.Produto;
import smit.teste.crud.repositories.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Integer id) {
        return produtoRepository.findById(id);
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deletar(Integer id) {
        produtoRepository.deleteById(id);
    }
}