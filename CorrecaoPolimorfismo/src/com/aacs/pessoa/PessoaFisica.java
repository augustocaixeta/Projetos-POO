package com.aacs.pessoa;

public class PessoaFisica extends Pessoa {
    private String CPF;

    public PessoaFisica(String CPF) {
        super();
    }

    public PessoaFisica(String nome, String CPF) {
        super(nome);
        setCPF(CPF);
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        if (CPF.trim().length() == 11 || CPF.trim().length() == 14) {
            this.CPF = CPF;
        } else {
            System.out.println("CPF Invalido.");
        }

        this.CPF = CPF;
    }
    
    @Override
    public void mostrarInfo() {
        System.out.println("Pessoa Fisica: " + super.getNome() + " - "
            + "CPF: " + this.getCPF());
    }
}
