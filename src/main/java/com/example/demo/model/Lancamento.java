package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "lancamentos") 
public class Lancamento {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private Integer idResponsavel;
    private String tipo;
    private int produtoId;
    private String nomeProduto;
    private Double preco;
    private String usuarioNome;

    public Lancamento() {
    }

    public Lancamento(String tipo, int produtoId, String nomeProduto, double preco, String usuarioNome, Integer idResponsavel) {
        this.tipo = tipo;
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.usuarioNome = usuarioNome;
        this.idResponsavel = idResponsavel;
    }

    public Integer getIdResponsavel() { return idResponsavel; }
    public void setIdResponsavel(Integer idResponsavel) { this.idResponsavel = idResponsavel; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getProdutoId() { return produtoId; }
    public void setProdutoId(int produtoId) { this.produtoId = produtoId; }

    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }

    public String getUsuarioNome() { return usuarioNome; }
    public void setUsuarioNome(String usuarioNome) { this.usuarioNome = usuarioNome; }
}