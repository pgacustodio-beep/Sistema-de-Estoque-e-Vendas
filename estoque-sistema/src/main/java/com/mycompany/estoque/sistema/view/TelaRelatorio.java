package com.mycompany.estoque.sistema.view;

import com.mycompany.estoque.sistema.controller.RelatorioController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaRelatorio extends JFrame {

    private JTextArea areaTexto;
    private RelatorioController controller = new RelatorioController();

    public TelaRelatorio() {
        setTitle("Relatórios do Sistema");
        setSize(600, 400);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // 🔹 Área de texto
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        add(new JScrollPane(areaTexto), BorderLayout.CENTER);

        // 🔹 Botões
        JPanel panelTop = new JPanel();

        JButton btnVendas = new JButton("Relatório de Vendas");
        JButton btnEstoque = new JButton("Relatório de Estoque");

        panelTop.add(btnVendas);
        panelTop.add(btnEstoque);

        add(panelTop, BorderLayout.NORTH);

        // 🔥 Eventos
        btnVendas.addActionListener(e -> carregarVendas());
        btnEstoque.addActionListener(e -> carregarEstoque());
    }

    private void carregarVendas() {
        areaTexto.setText("");
        List<String> lista = controller.vendas();

        for (String linha : lista) {
            areaTexto.append(linha + "\n");
        }
    }

    private void carregarEstoque() {
        areaTexto.setText("");
        List<String> lista = controller.estoque();

        for (String linha : lista) {
            areaTexto.append(linha + "\n");
        }
    }
}