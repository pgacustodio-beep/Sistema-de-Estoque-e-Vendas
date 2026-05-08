package com.mycompany.estoque.sistema.dao;

import com.mycompany.estoque.sistema.connection.ConnectionFactory;
import com.mycompany.estoque.sistema.model.ItemVenda;
import com.mycompany.estoque.sistema.model.Venda;

import java.sql.*;

public class VendaDAO {

    public int salvarVenda(Venda venda) {
        String sql = "INSERT INTO venda (total) VALUES (?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setBigDecimal(1, venda.getTotal());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    public void salvarItens(int vendaId, Venda venda) {
        String sql = "INSERT INTO item_venda (venda_id, produto_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (ItemVenda item : venda.getItens()) {
                stmt.setInt(1, vendaId);
                stmt.setInt(2, item.getProdutoId());
                stmt.setInt(3, item.getQuantidade());
                stmt.setBigDecimal(4, item.getPrecoUnitario());
                stmt.addBatch();
            }

            stmt.executeBatch();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}