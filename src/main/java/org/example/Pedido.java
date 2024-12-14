package org.example;

import java.util.ArrayList;

public class Pedido {
    private ArrayList<Produto> produtos;
    private String cliente;

    public Pedido(String cliente) {
        this.produtos = new ArrayList<>();
        this.cliente = cliente;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public float calcularTotal() {
        float total = 0;
        for (Produto produto : produtos) {
            total += produto.getPreco();
        }
        if (total > 100) {
            total *= 0.9; // Aplicar desconto de 10%
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder detalhes = new StringBuilder("Pedido do cliente: " + cliente + "\n");
        for (Produto produto : produtos) {
            detalhes.append(produto).append("\n");
        }
        detalhes.append("Total: R$ ").append(calcularTotal());
        return detalhes.toString();
    }
}
