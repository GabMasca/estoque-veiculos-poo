package br.com.fecaf.estoque.model;

import jakarta.persistence.*;

@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;
    private Integer ano;
    private String cor;
    private Double preco;
    private Integer quilometragem;
    private String status; // Ex: "Disponível" ou "Vendido"

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Integer quilometragem) {
        this.quilometragem = quilometragem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
}
