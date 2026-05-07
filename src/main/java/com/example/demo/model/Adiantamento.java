package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "adiantamentos") 
public class Adiantamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer idResponsavel;
    private Integer idProduto;
    private String nomeProdutor;
    private String nomeRecebeu;
    private Double valor;
    private Integer qtdMudas;
    private String data;
    private String usuarioNome;

    public Adiantamento() {}


    public Integer getIdResponsavel() { return idResponsavel; }
    public void setIdResponsavel(Integer idResponsavel) { this.idResponsavel = idResponsavel; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getIdProduto() { return idProduto; }
    public void setIdProduto(Integer idProduto) { this.idProduto = idProduto; }

    public String getNomeProdutor() { return nomeProdutor; }
    public void setNomeProdutor(String nomeProdutor) { this.nomeProdutor = nomeProdutor; }

    public String getNomeRecebeu() { return nomeRecebeu; }
    public void setNomeRecebeu(String nomeRecebeu) { this.nomeRecebeu = nomeRecebeu; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public Integer getQtdMudas() { return qtdMudas; }
    public void setQtdMudas(Integer qtdMudas) { this.qtdMudas = qtdMudas; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public String getUsuarioNome() { return usuarioNome; }
    public void setUsuarioNome(String usuarioNome) { this.usuarioNome = usuarioNome; }
}