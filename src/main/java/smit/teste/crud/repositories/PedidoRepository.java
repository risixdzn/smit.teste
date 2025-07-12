package smit.teste.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import smit.teste.crud.models.Pedido;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
}
