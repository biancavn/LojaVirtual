package org.example;

import java.util.ArrayList;

public class Catalogo {
    private ArrayList<Produto> produtos;

    public Catalogo() {
        this.produtos = new ArrayList<>();
    }
    public void adicionarProduto(Produto produto) {
        // Verificar se o ID já existe
        if (buscarProdutoPorId(produto.getId()) != null) {
            System.out.println("Erro: Produto com ID " + produto.getId() + " já existe no catálogo.");
            return;
        }
        produtos.add(produto);
        System.out.println("Produto adicionado: " + produto);
        System.out.println("Produto adicionado com sucesso!");
    }

    public void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto no catálogo.");
        } else {
            for (Produto produto : produtos) {
                System.out.println(produto);
            }
        }
    }

    public Produto buscarProdutoPorId(int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }
}
