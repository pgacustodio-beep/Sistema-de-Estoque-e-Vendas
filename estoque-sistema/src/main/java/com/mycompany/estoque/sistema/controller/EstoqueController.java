package com.mycompany.estoque.sistema.controller;

import com.mycompany.estoque.sistema.service.EstoqueService;

public class EstoqueController {

    private EstoqueService service = new EstoqueService();

    public void entrada(int produtoId, int quantidade) {
        service.entrada(produtoId, quantidade);
    }

    public void saida(int produtoId, int quantidade) {
        service.saida(produtoId, quantidade);
    }
}