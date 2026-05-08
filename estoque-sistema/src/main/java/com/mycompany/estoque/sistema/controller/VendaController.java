package com.mycompany.estoque.sistema.controller;

import com.mycompany.estoque.sistema.model.Venda;
import com.mycompany.estoque.sistema.service.VendaService;

public class VendaController {

    private VendaService service = new VendaService();

    public void finalizarVenda(Venda venda) {
        service.finalizarVenda(venda);
    }
}