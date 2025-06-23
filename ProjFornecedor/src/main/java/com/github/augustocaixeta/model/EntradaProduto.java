package com.github.augustocaixeta.model;

public class EntradaProduto {
    private Entrada entrada;
    private Produto produto;
    private int quantidade;

    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    @Override
    public String toString() {
        return "ID Entrada: " + getEntrada().getId() + "\n"
                + "ID Produto: " + getProduto().getId() + "\n"
                + "Produto: " + getProduto().getNome() + "\n"
                + "Valor: R$ " + getProduto().getValor() + "\n"
                + "Quantidade: " + getQuantidade() + "\n";
    }
}
