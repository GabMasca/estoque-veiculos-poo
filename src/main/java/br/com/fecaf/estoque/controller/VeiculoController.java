package br.com.fecaf.estoque.controller;

import br.com.fecaf.estoque.model.Marca;
import br.com.fecaf.estoque.model.Veiculo;
import br.com.fecaf.estoque.repository.MarcaRepository;
import br.com.fecaf.estoque.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @GetMapping
    public List<Veiculo> listar() {
        return veiculoRepository.findAll();
    }

    // Filtrar veículos por marca ou status
    @GetMapping("/filtro")
    public List<Veiculo> filtrar(
            @RequestParam(required = false) Long marcaId,
            @RequestParam(required = false) String status) {

        if (marcaId != null && status != null) {
            return veiculoRepository.findByMarcaIdAndStatus(marcaId, status);
        } else if (marcaId != null) {
            return veiculoRepository.findByMarcaId(marcaId);
        } else if (status != null) {
            return veiculoRepository.findByStatus(status);
        } else {
            return veiculoRepository.findAll();
        }
    }
    @PostMapping
    public Veiculo criar(@RequestBody Veiculo veiculo) {
        // Buscar marca pelo ID e vincular ao veículo
        if (veiculo.getMarca() != null && veiculo.getMarca().getId() != null) {
            Optional<Marca> marca = marcaRepository.findById(veiculo.getMarca().getId());
            marca.ifPresent(veiculo::setMarca);
        }
        return veiculoRepository.save(veiculo);
    }

    // Atualizar veículo existente
    @PutMapping("/{id}")
    public Veiculo atualizar(@PathVariable Long id, @RequestBody Veiculo dadosAtualizados) {
        return veiculoRepository.findById(id).map(veiculo -> {
            veiculo.setModelo(dadosAtualizados.getModelo());
            veiculo.setAno(dadosAtualizados.getAno());
            veiculo.setPreco(dadosAtualizados.getPreco());
            veiculo.setCor(dadosAtualizados.getCor());
            veiculo.setQuilometragem(dadosAtualizados.getQuilometragem());
            veiculo.setStatus(dadosAtualizados.getStatus());

            if (dadosAtualizados.getMarca() != null && dadosAtualizados.getMarca().getId() != null) {
                Optional<Marca> marca = marcaRepository.findById(dadosAtualizados.getMarca().getId());
                marca.ifPresent(veiculo::setMarca);
            }

            return veiculoRepository.save(veiculo);
        }).orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
    }
    // Excluir veículo
    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id) {
        if (!veiculoRepository.existsById(id)) {
            return "Veículo não encontrado.";
        }
        veiculoRepository.deleteById(id);
        return "Veículo removido com sucesso!";
    }

}
