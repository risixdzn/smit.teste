package smit.teste.crud.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smit.teste.crud.api.dtos.pedido.ItemPedidoCreateDTO;
import smit.teste.crud.api.dtos.pedido.PedidoCreateDTO;
import smit.teste.crud.models.ItemPedido;
import smit.teste.crud.models.Pedido;
import smit.teste.crud.models.Produto;
import smit.teste.crud.repositories.PedidoRepository;
import smit.teste.crud.repositories.ProdutoRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Integer id) {
        return pedidoRepository.findById(id);
    }

    public void deletar(Integer id) {
        pedidoRepository.deleteById(id);
    }

    @Transactional
    public Pedido criarPedido(PedidoCreateDTO dto) {
        Map<Integer, Produto> produtosMap = carregarProdutos(dto);

        /* Verifica se os produtos existem e têm estoque suficiente */
        for (ItemPedidoCreateDTO itemDto : dto.itens()) {
            Produto produto = produtosMap.get(itemDto.produtoId());
            if (produto == null) {
                throw new IllegalArgumentException("Produto não encontrado: " + itemDto.produtoId());
            }
            if (produto.getQuantidadeEmEstoque() < itemDto.quantidade()) {
                throw new IllegalArgumentException("Estoque insuficiente para o produto: " + produto.getNome());
            }
        }

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        List<ItemPedido> itens = new ArrayList<>();

        for (ItemPedidoCreateDTO itemDto : dto.itens()) {
            Produto produto = produtosMap.get(itemDto.produtoId());

            /* Atualiza o estoque do produto */
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - itemDto.quantidade());

            /* Cria o item do pedido */
            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setQuantidade(itemDto.quantidade());

            itens.add(item);
        }

        pedido.setItens(itens);
        return pedidoRepository.save(pedido);
    }

    /* Obtém os produtos de um pedido para acesso por ID
    * (utilizado para verificar se os produtos existem antes de criar um pedido) */
    private Map<Integer, Produto> carregarProdutos(PedidoCreateDTO dto) {
        Set<Integer> ids = dto.itens().stream()
                .map(ItemPedidoCreateDTO::produtoId)
                .collect(Collectors.toSet());

        List<Produto> produtos = produtoRepository.findAllById(ids);

        Map<Integer, Produto> map = new HashMap<>();
        for (Produto p : produtos) {
            map.put(p.getId(), p);
        }

        return map;
    }
}
