package com.mycompany.estoque.sistema.controller;

import com.mycompany.estoque.sistema.model.Produto;
import com.mycompany.estoque.sistema.service.ProdutoService;

import java.util.List;

public class ProdutoController {

    private ProdutoService service = new ProdutoService();

    public void salvar(Produto produto) {
        service.salvar(produto);
    }

    public List<Produto> listar() {
        return service.listar();
    }

    public void atualizar(Produto produto) {
        service.atualizar(produto);
    }

    public void deletar(int id) {
        service.deletar(id);
    }
}