package com.aacs.pessoa;

public class PessoaJuridica extends Pessoa {
    private String CNPJ;
    
    public PessoaJuridica() {
        super();
    }
    
    public PessoaJuridica(String nome, String CNPJ) {
        super(nome);
        this.setCNPJ(CNPJ);
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        if (CNPJ.trim().length() == 14 || CNPJ.trim().length() == 18) {
            this.CNPJ = CNPJ;
        } else {
            System.out.println("CNPJ Invalido.");
        }
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Pessoa Juridica: " + super.getNome() + " - "
                + "CNPJ: " + this.getCNPJ());
    }
}
