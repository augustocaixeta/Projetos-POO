package com.github.augustocaixeta.objetos;

import java.util.ArrayList;
import java.util.List;

public class Propietario {
    private String nome;
    private String CPF;
    private String telefone;
    private String email;
    private List<Endereco> listaEnderecos;
    private List<Pet> listaPets;
    
    public Propietario() {
        listaEnderecos = new ArrayList<>();
        listaPets = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Endereco> getListaEnderecos() {
        return listaEnderecos;
    }

    public void setListaEnderecos(List<Endereco> listaEnderecos) {
        this.listaEnderecos = listaEnderecos;
    }

    public List<Pet> getListaPets() {
        return listaPets;
    }

    public void setListaPets(List<Pet> listaPets) {
        this.listaPets = listaPets;
    }
}
