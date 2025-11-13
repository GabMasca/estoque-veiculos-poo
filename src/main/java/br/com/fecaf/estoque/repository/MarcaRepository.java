package br.com.fecaf.estoque.repository;

import br.com.fecaf.estoque.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
}
