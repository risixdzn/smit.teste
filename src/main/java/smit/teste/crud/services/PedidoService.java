package smit.teste.crud.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smit.teste.crud.models.Pedido;
import smit.teste.crud.repositories.PedidoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(UUID id) {
        return pedidoRepository.findById(id);
    }

    public Pedido salvar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void deletar(UUID id) {
        pedidoRepository.deleteById(id);
    }
}
