package br.com.fecaf.estoque.repository;

import br.com.fecaf.estoque.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    List<Veiculo> findByMarcaId(Long marcaId);

    List<Veiculo> findByStatus(String status);

    List<Veiculo> findByMarcaIdAndStatus(Long marcaId, String status);
}
