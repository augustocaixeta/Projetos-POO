package com.github.augustocaixeta.model;

public class Contato {
    private int id;
    private String tipo;
    private String contato;
    private Fornecedor fornecedor;
    
    public Contato(int id, String tipo, String contato, Fornecedor fornecedor) {
        this.id = id;
        this.tipo = tipo;
        this.contato = contato;
        this.fornecedor = fornecedor;
    }
    
    public Contato(String tipo, String contato, Fornecedor fornecedor) {
        this.tipo = tipo;
        this.contato = contato;
        this.fornecedor = fornecedor;
    }

    public Contato() {
        super();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    @Override
    public String toString() {
        return "ID: " + getId() + "\n"
                + "Tipo: " + getTipo() + "\n"
                + "Contato: " + getContato() + "\n"
                + "Fornecedor: " + getFornecedor().getNome() + "\n";
    }
}
