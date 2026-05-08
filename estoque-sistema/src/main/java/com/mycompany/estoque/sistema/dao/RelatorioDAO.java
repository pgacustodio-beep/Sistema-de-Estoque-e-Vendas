package com.mycompany.estoque.sistema.dao;

import com.mycompany.estoque.sistema.connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAO {

    // 🔹 RELATÓRIO DE VENDAS
    public List<String> relatorioVendas() {
        List<String> lista = new ArrayList<>();

        String sql = """
                SELECT v.id, v.data, v.total
                FROM venda v
                ORDER BY v.data DESC
                """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String linha = "Venda #" + rs.getInt("id") +
                        " | Data: " + rs.getTimestamp("data") +
                        " | Total: R$ " + rs.getBigDecimal("total");

                lista.add(linha);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    // 🔹 RELATÓRIO DE ESTOQUE
    public List<String> relatorioEstoque() {
        List<String> lista = new ArrayList<>();

        String sql = "SELECT nome, quantidade, preco FROM produto";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String linha = rs.getString("nome") +
                        " | Qtd: " + rs.getInt("quantidade") +
                        " | Preço: R$ " + rs.getBigDecimal("preco");

                lista.add(linha);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
}