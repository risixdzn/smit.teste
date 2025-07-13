package smit.teste.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import smit.teste.crud.models.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
