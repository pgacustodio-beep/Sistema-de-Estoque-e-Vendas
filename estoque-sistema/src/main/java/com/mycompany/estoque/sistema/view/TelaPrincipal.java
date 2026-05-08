package com.mycompany.estoque.sistema.view;

import javax.swing.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Sistema de Estoque");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuCadastro = new JMenu("Cadastro");
        JMenuItem itemProduto = new JMenuItem("Produtos");

        itemProduto.addActionListener(e -> new TelaProduto().setVisible(true));

        menuCadastro.add(itemProduto);

        JMenu menuVenda = new JMenu("Vendas");
        JMenuItem itemVenda = new JMenuItem("Nova Venda");

        itemVenda.addActionListener(e -> new TelaVenda().setVisible(true));

        menuVenda.add(itemVenda);

        menuBar.add(menuCadastro);
        menuBar.add(menuVenda);

        setJMenuBar(menuBar);
        
        JMenu menuRelatorio = new JMenu("Relatórios");
        JMenuItem itemRelatorio = new JMenuItem("Abrir Relatórios");

        itemRelatorio.addActionListener(e -> new TelaRelatorio().setVisible(true));

        menuRelatorio.add(itemRelatorio);
        menuBar.add(menuRelatorio);
    }
}