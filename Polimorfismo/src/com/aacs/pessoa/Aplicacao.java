package com.aacs.pessoa;

public class Aplicacao {
    public static void main(String[] args) {
        Pessoa p1 = new PessoaFisica();
        p1.setIdentificacao("000.000.000-00");
        
        Pessoa p2 = new PessoaJuridica();
        p2.setIdentificacao("12.345.678/0001-95");
        
        System.out.println(p1.getIdentificacao() + " (Valido: " + p1.isDocumentoValido() + ")");
        System.out.println(p2.getIdentificacao() + " (Valido: " + p2.isDocumentoValido() + ")");
    }
}
