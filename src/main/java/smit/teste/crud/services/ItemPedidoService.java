package smit.teste.crud.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smit.teste.crud.models.ItemPedido;
import smit.teste.crud.repositories.ItemPedidoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemPedidoService {
    private final ItemPedidoRepository itemPedidoRepository;

    public List<ItemPedido> listarTodos() {
        return itemPedidoRepository.findAll();
    }

    public Optional<ItemPedido> buscarPorId(Integer id) {
        return itemPedidoRepository.findById(id);
    }

    public ItemPedido salvar(ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }

    public void deletar(Integer id) {
        itemPedidoRepository.deleteById(id);
    }
}
