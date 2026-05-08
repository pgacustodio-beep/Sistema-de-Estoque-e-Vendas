package com.mycompany.estoque.sistema.controller;

import com.mycompany.estoque.sistema.service.RelatorioService;

import java.util.List;

public class RelatorioController {

    private RelatorioService service = new RelatorioService();

    public List<String> vendas() {
        return service.vendas();
    }

    public List<String> estoque() {
        return service.estoque();
    }
}