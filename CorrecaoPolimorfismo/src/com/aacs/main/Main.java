package com.aacs.main;

import com.aacs.pessoa.Pessoa;
import com.aacs.pessoa.PessoaFisica;
import com.aacs.pessoa.PessoaJuridica;

public class Main {
    public static void main(String[] args) {
        Pessoa f1 = new PessoaFisica("F1", "111.222.333-44");
        Pessoa j1 = new PessoaJuridica("J1", "22233344455566");
        
        f1.mostrarInfo();
        j1.mostrarInfo();
        
        Pessoa f2 = new PessoaFisica("F2", "333.444.555-66");
        Pessoa f3 = new PessoaFisica("F3", "444.555.666-77");
    
        f2 = f3;
        f2.mostrarInfo();
    }
}
