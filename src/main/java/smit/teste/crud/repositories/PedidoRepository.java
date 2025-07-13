package smit.teste.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import smit.teste.crud.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
