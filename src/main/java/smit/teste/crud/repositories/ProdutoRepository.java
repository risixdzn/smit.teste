package smit.teste.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import smit.teste.crud.models.Produto;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
}
