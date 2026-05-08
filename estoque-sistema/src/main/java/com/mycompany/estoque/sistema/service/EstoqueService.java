package com.mycompany.estoque.sistema.service;

import com.mycompany.estoque.sistema.dao.MovimentacaoEstoqueDAO;
import com.mycompany.estoque.sistema.dao.ProdutoDAO;
import com.mycompany.estoque.sistema.model.MovimentacaoEstoque;
import com.mycompany.estoque.sistema.model.Produto;

public class EstoqueService {

    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private MovimentacaoEstoqueDAO movimentacaoDAO = new MovimentacaoEstoqueDAO();

    // 🔹 ENTRADA DE ESTOQUE
    public void entrada(int produtoId, int quantidade) {

        Produto produto = produtoDAO.listar()
                .stream()
                .filter(p -> p.getId() == produtoId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setQuantidade(produto.getQuantidade() + quantidade);
        produtoDAO.atualizar(produto);

        MovimentacaoEstoque mov = new MovimentacaoEstoque();
        mov.setProdutoId(produtoId);
        mov.setTipo("ENTRADA");
        mov.setQuantidade(quantidade);

        movimentacaoDAO.registrar(mov);
    }

    // 🔹 SAÍDA DE ESTOQUE
    public void saida(int produtoId, int quantidade) {

        Produto produto = produtoDAO.listar()
                .stream()
                .filter(p -> p.getId() == produtoId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (produto.getQuantidade() < quantidade) {
            throw new RuntimeException("Estoque insuficiente!");
        }

        produto.setQuantidade(produto.getQuantidade() - quantidade);
        produtoDAO.atualizar(produto);

        MovimentacaoEstoque mov = new MovimentacaoEstoque();
        mov.setProdutoId(produtoId);
        mov.setTipo("SAIDA");
        mov.setQuantidade(quantidade);

        movimentacaoDAO.registrar(mov);
    }
}