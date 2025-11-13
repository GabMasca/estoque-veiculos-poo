package br.com.fecaf.estoque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Diz que essa classe será uma tabela no banco de dados
public class Marca {

    @Id // Define a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Faz o ID ser gerado automaticamente
    private Long id;

    private String nome;

    // Construtor vazio - necessário para o JPA funcionar
    public Marca() {
    }

    // Construtor completo
    public Marca(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
