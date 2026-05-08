package com.mycompany.estoque.sistema.dao;

import com.mycompany.estoque.sistema.connection.ConnectionFactory;
import com.mycompany.estoque.sistema.model.MovimentacaoEstoque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovimentacaoEstoqueDAO {

    public void registrar(MovimentacaoEstoque mov) {
        String sql = "INSERT INTO movimentacao_estoque (produto_id, tipo, quantidade) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, mov.getProdutoId());
            stmt.setString(2, mov.getTipo());
            stmt.setInt(3, mov.getQuantidade());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}