package com.mycompany.estoque.sistema.service;

import com.mycompany.estoque.sistema.dao.RelatorioDAO;

import java.util.List;

public class RelatorioService {

    private RelatorioDAO dao = new RelatorioDAO();

    public List<String> vendas() {
        return dao.relatorioVendas();
    }

    public List<String> estoque() {
        return dao.relatorioEstoque();
    }
}