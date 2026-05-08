package com.mycompany.estoque.sistema.model;

public class MovimentacaoEstoque {

    private int id;
    private int produtoId;
    private String tipo; // ENTRADA ou SAIDA
    private int quantidade;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getProdutoId() { return produtoId; }
    public void setProdutoId(int produtoId) { this.produtoId = produtoId; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}