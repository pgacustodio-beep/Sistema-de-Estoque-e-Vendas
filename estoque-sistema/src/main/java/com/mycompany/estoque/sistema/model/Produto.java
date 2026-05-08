package com.mycompany.estoque.sistema.model;

import java.math.BigDecimal;

public class Produto {

    private int id;
    private String nome;
    private String codigoBarras;
    private BigDecimal preco;
    private int quantidade;

    public Produto() {}

    public Produto(String nome, String codigoBarras, BigDecimal preco, int quantidade) {
        this.nome = nome;
        this.codigoBarras = codigoBarras;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // GETTERS E SETTERS

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    @Override
     public String toString() {
    return nome;
    }
    public String getCodigoBarras() { return codigoBarras; }
    public void setCodigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}