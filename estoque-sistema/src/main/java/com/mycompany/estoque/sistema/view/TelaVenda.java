package com.mycompany.estoque.sistema.view;

import com.mycompany.estoque.sistema.controller.ProdutoController;
import com.mycompany.estoque.sistema.controller.VendaController;
import com.mycompany.estoque.sistema.model.ItemVenda;
import com.mycompany.estoque.sistema.model.Produto;
import com.mycompany.estoque.sistema.model.Venda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;
import java.text.NumberFormat;
import java.util.Locale;

public class TelaVenda extends JFrame {

    private JComboBox<Produto> comboProdutos;
    private JTextField txtQuantidade;
    private JLabel lblTotal;

    private JTable tabela;
    private DefaultTableModel tableModel;

    private ProdutoController produtoController = new ProdutoController();
    private VendaController vendaController = new VendaController();

    private Venda venda = new Venda();

    public TelaVenda() {
        setTitle("PDV - Sistema de Vendas");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // 🔹 TOPO (seleção de produto)
        JPanel panelTop = new JPanel(new GridLayout(2, 3, 10, 10));
        panelTop.setBorder(BorderFactory.createTitledBorder("Adicionar Produto"));

        comboProdutos = new JComboBox<>();
        carregarProdutos();

        txtQuantidade = new JTextField();

        JButton btnAdicionar = new JButton("Adicionar");

        panelTop.add(new JLabel("Produto:"));
        panelTop.add(new JLabel("Quantidade:"));
        panelTop.add(new JLabel(""));

        panelTop.add(comboProdutos);
        panelTop.add(txtQuantidade);
        panelTop.add(btnAdicionar);

        add(panelTop, BorderLayout.NORTH);

        // 🔹 TABELA (carrinho)
        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Produto", "Qtd", "Preço", "Subtotal"}, 0
        );

        tabela = new JTable(tableModel);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        // 🔹 RODAPÉ
        JPanel panelBottom = new JPanel(new BorderLayout());

        lblTotal = new JLabel("Total: R$ 0.00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 18));

        JButton btnFinalizar = new JButton("Finalizar Venda");

        panelBottom.add(lblTotal, BorderLayout.WEST);
        panelBottom.add(btnFinalizar, BorderLayout.EAST);

        add(panelBottom, BorderLayout.SOUTH);

        // 🔥 EVENTOS
        btnAdicionar.addActionListener(e -> adicionarItem());
        btnFinalizar.addActionListener(e -> finalizarVenda());
    }

    // 🔹 CARREGAR PRODUTOS NO COMBO
    private void carregarProdutos() {
        List<Produto> lista = produtoController.listar();
        for (Produto p : lista) {
            comboProdutos.addItem(p);
        }
    }

    // 🔹 ADICIONAR AO CARRINHO
    private void adicionarItem() {
        try {
            Produto produto = (Produto) comboProdutos.getSelectedItem();
            int qtd = Integer.parseInt(txtQuantidade.getText());

            if (produto == null) return;

            ItemVenda item = new ItemVenda();
            item.setProdutoId(produto.getId());
            item.setNomeProduto(produto.getNome());
            item.setQuantidade(qtd);
            item.setPrecoUnitario(produto.getPreco());

            venda.adicionarItem(item);

            BigDecimal subtotal = produto.getPreco()
                    .multiply(new BigDecimal(qtd));

            tableModel.addRow(new Object[]{
                    produto.getId(),
                    produto.getNome(),
                    qtd,
                    produto.getPreco(),
                    subtotal
            });

            atualizarTotal();
            txtQuantidade.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }

    // 🔹 CALCULAR TOTAL
    private void atualizarTotal() {
        BigDecimal total = BigDecimal.ZERO;

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            BigDecimal subtotal = (BigDecimal) tableModel.getValueAt(i, 4);
            total = total.add(subtotal);
        }

        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        lblTotal.setText("Total: " + nf.format(total));
        lblTotal.setText("Total: R$ " + total);
        
    }

    // 🔹 FINALIZAR VENDA
    private void finalizarVenda() {
        try {
            if (venda.getItens().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Carrinho vazio!");
                return;
            }

            vendaController.finalizarVenda(venda);

            JOptionPane.showMessageDialog(this, "Venda realizada com sucesso!");

            limparVenda();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }

    // 🔹 LIMPAR VENDA
    private void limparVenda() {
        venda = new Venda();
        tableModel.setRowCount(0);
        lblTotal.setText("Total: R$ 0.00");
    }
}