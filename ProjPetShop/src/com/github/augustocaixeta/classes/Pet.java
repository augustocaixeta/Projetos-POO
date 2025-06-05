package com.github.augustocaixeta.classes;

public class Pet {
    private String nome;
    private int idade;
    private String tipo;
    private String raca;
    private float peso;
    private Propietario propietario;

    public Pet(String nome, int idade, String tipo, String raca, float peso, Propietario propietario) {
        this.nome = nome;
        this.idade = idade;
        this.tipo = tipo;
        this.raca = raca;
        this.peso = peso;
        this.propietario = propietario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }
    
    @Override
    public String toString() {
        return "Nome: " + nome
                + "\nIdade: " + idade + " anos"
                + "\nTipo: " + tipo
                + "\nPeso: " + peso + "Kg"
                + "\nPropietario: " + propietario.getNome()
                + "\n";
                
    }
}
