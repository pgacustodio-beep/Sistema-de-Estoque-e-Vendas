package com.mycompany.estoque.sistema.view;

import com.mycompany.estoque.sistema.controller.UsuarioController;
import com.mycompany.estoque.sistema.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {

    private JTextField txtLogin;
    private JPasswordField txtSenha;

    private UsuarioController controller = new UsuarioController();

    public TelaLogin() {
        setTitle("Login do Sistema");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Usuário:"));
        txtLogin = new JTextField();
        add(txtLogin);

        add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        add(txtSenha);

        JButton btnLogin = new JButton("Entrar");
        add(new JLabel()); // espaço
        add(btnLogin);

        btnLogin.addActionListener(e -> fazerLogin());
    }

    private void fazerLogin() {
        try {
            String login = txtLogin.getText();
            String senha = new String(txtSenha.getPassword());

            Usuario usuario = controller.login(login, senha);

            JOptionPane.showMessageDialog(this, "Bem-vindo, " + usuario.getLogin());

            // abrir sistema principal
            new TelaPrincipal().setVisible(true);
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}