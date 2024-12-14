package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Produto {
    private int id;
    private String nome;
    private float preco;

    public Produto(int id, String nome, float preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public float getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Preço: R$ " + preco;
    }
}
