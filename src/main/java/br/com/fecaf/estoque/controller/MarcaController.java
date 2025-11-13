package br.com.fecaf.estoque.controller;

import br.com.fecaf.estoque.model.Marca;
import br.com.fecaf.estoque.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRepository;

    // Listar todas as marcas
    @GetMapping
    public List<Marca> listar() {
        return marcaRepository.findAll();
    }

    // Cadastrar nova marca
    @PostMapping
    public Marca cadastrar(@RequestBody Marca marca) {
        return marcaRepository.save(marca);
    }

    // Atualizar marca existente
    @PutMapping("/{id}")
    public Marca atualizar(@PathVariable Long id, @RequestBody Marca marcaNova) {
        Marca marca = marcaRepository.findById(id).orElse(null);

        if (marca != null) {
            marca.setNome(marcaNova.getNome());
            return marcaRepository.save(marca);
        }

        return null;
    }

    // Deletar marca pelo ID
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        marcaRepository.deleteById(id);
    }
}
