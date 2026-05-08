package com.mycompany.estoque.sistema.view;

import com.mycompany.estoque.sistema.controller.ProdutoController;
import com.mycompany.estoque.sistema.model.Produto;
import com.mycompany.estoque.sistema.controller.EstoqueController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public class TelaProduto extends JFrame {

    private JTextField txtNome;
    private JTextField txtCodigo;
    private JTextField txtPreco;
    private JTextField txtQuantidade;

    private JTable tabela;
    private DefaultTableModel tableModel;

    private ProdutoController controller = new ProdutoController();
    private int idSelecionado = -1;

    public TelaProduto() {
        setTitle("Controle de Produtos");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // 🔹 Painel de formulário
        JPanel panelForm = new JPanel(new GridLayout(2, 4, 10, 10));
        panelForm.setBorder(BorderFactory.createTitledBorder("Cadastro de Produto"));

        txtNome = new JTextField();
        txtCodigo = new JTextField();
        txtPreco = new JTextField();
        txtQuantidade = new JTextField();

        panelForm.add(new JLabel("Nome:"));
        panelForm.add(new JLabel("Código:"));
        panelForm.add(new JLabel("Preço:"));
        panelForm.add(new JLabel("Quantidade:"));

        panelForm.add(txtNome);
        panelForm.add(txtCodigo);
        panelForm.add(txtPreco);
        panelForm.add(txtQuantidade);

        add(panelForm, BorderLayout.NORTH);

        // 🔹 Tabela
        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Nome", "Código", "Preço", "Quantidade"}, 0
        );
        tabela = new JTable(tableModel);
        JScrollPane scroll = new JScrollPane(tabela);

        add(scroll, BorderLayout.CENTER);

        // 🔹 Painel de botões
        JPanel panelButtons = new JPanel();

        JButton btnSalvar = new JButton("Salvar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");

        panelButtons.add(btnSalvar);
        panelButtons.add(btnAtualizar);
        panelButtons.add(btnExcluir);
        
        JButton btnEntrada = new JButton("Entrada Estoque");
        JButton btnSaida = new JButton("Saída Estoque");

        panelButtons.add(btnEntrada);
        panelButtons.add(btnSaida);

        add(panelButtons, BorderLayout.SOUTH);

        // 🔥 Eventos
        
        btnSalvar.addActionListener(e -> salvar());
        btnAtualizar.addActionListener(e -> atualizar());
        btnExcluir.addActionListener(e -> excluir());

        tabela.getSelectionModel().addListSelectionListener(e -> preencherCampos());

        listarProdutos();
        
        EstoqueController estoqueController = new EstoqueController();

        btnEntrada.addActionListener(e -> {
           if (idSelecionado == -1) {
        JOptionPane.showMessageDialog(this, "Selecione um produto!");
        return;
    }

        String qtd = JOptionPane.showInputDialog("Quantidade de entrada:");

        estoqueController.entrada(idSelecionado, Integer.parseInt(qtd));
        listarProdutos();
    });

        btnSaida.addActionListener(e -> {
           if (idSelecionado == -1) {
        JOptionPane.showMessageDialog(this, "Selecione um produto!");
        return;
    }

        String qtd = JOptionPane.showInputDialog("Quantidade de saída:");

        estoqueController.saida(idSelecionado, Integer.parseInt(qtd));
        listarProdutos();
    });
    }

    // 🔹 MÉTODOS

    private void salvar() {
        try {
            Produto p = new Produto();
            p.setNome(txtNome.getText());
            p.setCodigoBarras(txtCodigo.getText());
            p.setPreco(new BigDecimal(txtPreco.getText()));
            p.setQuantidade(Integer.parseInt(txtQuantidade.getText()));

            controller.salvar(p);
            limparCampos();
            listarProdutos();

            JOptionPane.showMessageDialog(this, "Produto salvo com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }

    private void atualizar() {
        if (idSelecionado == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto!");
            return;
        }

        try {
            Produto p = new Produto();
            p.setId(idSelecionado);
            p.setNome(txtNome.getText());
            p.setCodigoBarras(txtCodigo.getText());
            p.setPreco(new BigDecimal(txtPreco.getText()));
            p.setQuantidade(Integer.parseInt(txtQuantidade.getText()));

            controller.atualizar(p);
            limparCampos();
            listarProdutos();

            JOptionPane.showMessageDialog(this, "Atualizado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }

    private void excluir() {
        if (idSelecionado == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Deseja excluir?");
        if (confirm == JOptionPane.YES_OPTION) {
            controller.deletar(idSelecionado);
            limparCampos();
            listarProdutos();
        }
    }

    private void listarProdutos() {
        tableModel.setRowCount(0);

        List<Produto> lista = controller.listar();

        for (Produto p : lista) {
            tableModel.addRow(new Object[]{
                    p.getId(),
                    p.getNome(),
                    p.getCodigoBarras(),
                    p.getPreco(),
                    p.getQuantidade()
            });
        }
    }

    private void preencherCampos() {
        int linha = tabela.getSelectedRow();

        if (linha != -1) {
            idSelecionado = (int) tabela.getValueAt(linha, 0);
            txtNome.setText(tabela.getValueAt(linha, 1).toString());
            txtCodigo.setText(tabela.getValueAt(linha, 2).toString());
            txtPreco.setText(tabela.getValueAt(linha, 3).toString());
            txtQuantidade.setText(tabela.getValueAt(linha, 4).toString());
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtCodigo.setText("");
        txtPreco.setText("");
        txtQuantidade.setText("");
        idSelecionado = -1;
    }
   
}