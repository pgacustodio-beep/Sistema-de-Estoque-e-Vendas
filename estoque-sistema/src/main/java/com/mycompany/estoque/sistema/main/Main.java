package com.mycompany.estoque.sistema.main;

import com.mycompany.estoque.sistema.connection.ConnectionFactory;
import java.sql.Connection;
import com.mycompany.estoque.sistema.view.TelaProduto;
import com.mycompany.estoque.sistema.view.TelaVenda;
import com.mycompany.estoque.sistema.view.TelaLogin;
import com.mycompany.estoque.sistema.view.TelaRelatorio;
import com.mycompany.estoque.sistema.view.TelaPrincipal;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        
        try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
        e.printStackTrace();
}
        SwingUtilities.invokeLater(() -> {
        
        Connection conn = ConnectionFactory.getConnection();
        System.out.println("Conectado com sucesso!");
        new TelaLogin().setVisible(true);
        new TelaPrincipal().setVisible(true);
        new TelaProduto().setVisible(true);
        new TelaVenda().setVisible(true);
        new TelaRelatorio().setVisible(true);
        
        });
    }
}


