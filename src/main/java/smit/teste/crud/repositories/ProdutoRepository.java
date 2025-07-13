package smit.teste.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import smit.teste.crud.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
