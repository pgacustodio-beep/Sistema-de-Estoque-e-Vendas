package com.mycompany.estoque.sistema.service;

import com.mycompany.estoque.sistema.dao.VendaDAO;
import com.mycompany.estoque.sistema.model.ItemVenda;
import com.mycompany.estoque.sistema.model.Venda;

import java.math.BigDecimal;

public class VendaService {

    private VendaDAO vendaDAO = new VendaDAO();
    private EstoqueService estoqueService = new EstoqueService();

    public void finalizarVenda(Venda venda) {

        BigDecimal total = BigDecimal.ZERO;

        for (ItemVenda item : venda.getItens()) {
            BigDecimal subtotal = item.getPrecoUnitario()
                    .multiply(new BigDecimal(item.getQuantidade()));

            total = total.add(subtotal);

            // baixa no estoque
            estoqueService.saida(item.getProdutoId(), item.getQuantidade());
        }

        venda.setTotal(total);

        int vendaId = vendaDAO.salvarVenda(venda);
        vendaDAO.salvarItens(vendaId, venda);
    }
}