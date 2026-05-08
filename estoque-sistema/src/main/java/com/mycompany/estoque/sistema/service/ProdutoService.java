package com.mycompany.estoque.sistema.service;

import com.mycompany.estoque.sistema.dao.ProdutoDAO;
import com.mycompany.estoque.sistema.model.Produto;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoService {

    private ProdutoDAO dao = new ProdutoDAO();

    public void salvar(Produto produto) {
        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new RuntimeException("Nome do produto é obrigatório");
        }

        if (produto.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Preço deve ser maior que zero");
        }

        dao.salvar(produto);
    }

    public List<Produto> listar() {
        return dao.listar();
    }

    public void atualizar(Produto produto) {
        dao.atualizar(produto);
    }

    public void deletar(int id) {
        dao.deletar(id);
    }
}