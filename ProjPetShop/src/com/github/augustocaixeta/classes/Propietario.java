package com.github.augustocaixeta.classes;

import java.util.ArrayList;
import java.util.List;

public class Propietario {
    private String nome;
    private String CPF;
    private String telefone;
    private String email;
    private List<Endereco> listaEnderecos = new ArrayList<>();
    private List<Pet> listaPets = new ArrayList<>();

    public Propietario(String nome, String CPF, String telefone, String email) {
        this.nome = nome;
        this.CPF = CPF;
        this.telefone = telefone;
        this.email = email;
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

    public void adicionarEndereco(Endereco endereco) {
        listaEnderecos.add(endereco);
    }
    
    public List<Pet> getListaPets() {
        return listaPets;
    }

    public void setListaPets(List<Pet> listaPets) {
        this.listaPets = listaPets;
    }
    
    public void adicionarPet(Pet pet) {
        listaPets.add(pet);
    }
    
    @Override
    public String toString() {
        return "Nome: " + nome
                + "\nCPF: " + CPF
                + "\nTelefone: " + telefone
                + "\nEmail: " + email;
    }
}