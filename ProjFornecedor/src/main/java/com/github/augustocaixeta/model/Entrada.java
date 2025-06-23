package com.github.augustocaixeta.model;

import java.time.LocalDate;
import java.util.List;

public class Entrada {
    private int id;
    private double totalEntrada;
    private LocalDate dataEntrada;
    private Fornecedor fornecedor;
    private List<EntradaProduto> itensEntrada;

    public Entrada(int id, int totalEntrada, LocalDate dataEntrada) {
        this.id = id;
        this.totalEntrada = totalEntrada;
        this.dataEntrada = dataEntrada;
    }

    public Entrada() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalEntrada() {
        return totalEntrada;
    }

    public void setTotalEntrada(double totalEntrada) {
        this.totalEntrada = totalEntrada;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<EntradaProduto> getItensEntrada() {
        return itensEntrada;
    }

    public void setItensEntrada(List<EntradaProduto> itensEntrada) {
        this.itensEntrada = itensEntrada;
    }
    
    @Override
    public String toString() {
        return "ID: " + getId() + ""
                + "Data da entrada: " + getDataEntrada() + "\n"
                + "Total da entrada: " + getTotalEntrada() + "\n"
                + "Fornecedor: " + getFornecedor().getNome() + "\n";
    }
}
