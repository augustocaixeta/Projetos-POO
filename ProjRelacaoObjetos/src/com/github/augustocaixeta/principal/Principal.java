package com.github.augustocaixeta.principal;

import com.github.augustocaixeta.objetos.Contato;
import com.github.augustocaixeta.objetos.Pessoa;
import com.github.augustocaixeta.util.MostrarDados;

import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        Pessoa p1 = new Pessoa();
        Pessoa p2 = new Pessoa();
        
        // Contatos
        Contato c1 = new Contato();
        c1.setContatoId(1);
        c1.setTipoContato("Celular");
        c1.setNumeroContato("(34) 9 0000-1111");
        c1.setPessoa(p1);
        
        Contato c2 = new Contato();
        c2.setContatoId(2);
        c2.setTipoContato("Celular");
        c2.setNumeroContato("(34) 9 1111-2222");
        c2.setPessoa(p1);
        
        Contato c3 = new Contato();
        c3.setContatoId(3);
        c3.setTipoContato("Celular");
        c3.setNumeroContato("(34) 9 2222-3333");
        c3.setPessoa(p2);
        
        // Pessoas
        p1.setPessoaId(1);
        p1.setNome("Pessoa A");
        p1.setDataNascimento("01/10/2000");
        p1.setCPF("000.111.222-33");
        List<Contato> listaContatos1 = p1.getContatos();
        listaContatos1.add(c1);
        listaContatos1.add(c2);
        p1.setContatos(listaContatos1);
        
        p2.setPessoaId(2);
        p2.setNome("Pessoa B");
        p2.setDataNascimento("01/11/2000");
        p2.setCPF("111.222.333-44");
        List<Contato> listaContatos2 = p2.getContatos();
        listaContatos2.add(c3);
        p1.setContatos(listaContatos2);
        
        MostrarDados md = new MostrarDados();
        
        // Mostrar Contato
        md.mostrarDadosContato(c1);
        md.mostrarDadosContato(c2);
        md.mostrarDadosContato(c3);
        
        // Mostrar Pessoa
        md.mostrarDadosPessoa(p1);
        md.mostrarDadosPessoa(p2);
    }
}