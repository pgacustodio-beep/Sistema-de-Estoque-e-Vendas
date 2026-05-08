package com.mycompany.estoque.sistema.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Venda {

    private int id;
    private BigDecimal total;
    private List<ItemVenda> itens = new ArrayList<>();

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public List<ItemVenda> getItens() { return itens; }

    public void adicionarItem(ItemVenda item) {
        itens.add(item);
    }
}