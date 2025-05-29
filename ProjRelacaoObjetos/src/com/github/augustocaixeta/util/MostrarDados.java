package com.github.augustocaixeta.util;

import com.github.augustocaixeta.objetos.Contato;
import com.github.augustocaixeta.objetos.Pessoa;

public class MostrarDados {
    public void mostrarDadosPessoa(Pessoa pessoa) {
        System.out.println("********** DADOS PESSOA **********");
        System.out.println("ID: " + pessoa.getPessoaId());
        System.out.println("Nome: " + pessoa.getNome());
        System.out.println("CPF: " + pessoa.getCPF());
        System.out.println("Data de nascimento: " + pessoa.getDataNascimento());
        
        System.out.println("************ CONTATOS ************");
        for (Contato contato : pessoa.getContatos()) {
            System.out.println("ID: " + contato.getContatoId());
            System.out.println("Tipo: " + contato.getTipoContato());
            System.out.println("Numero: " + contato.getNumeroContato());
        }
    }
    
    public void mostrarDadosContato(Contato contato) {
        System.out.println("********** DADOS CONTATO **********");
        System.out.println("ID: " + contato.getContatoId());
        System.out.println("Tipo: " + contato.getTipoContato());
        System.out.println("Numero: " + contato.getNumeroContato());
        System.out.println("************* PESSOA *************");
        System.out.println("ID: " + contato.getPessoa().getPessoaId());
        System.out.println("Nome: " + contato.getPessoa().getNome());
        System.out.println("CPF: " + contato.getPessoa().getCPF());
        System.out.println("Data de nascimento: " + contato.getPessoa().getDataNascimento());
    }
}
