package com.github.augustocaixeta.principal;

import com.github.augustocaixeta.objetos.Endereco;
import com.github.augustocaixeta.objetos.Pet;
import com.github.augustocaixeta.objetos.Propietario;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        Endereco e1 = new Endereco();
        Pet p1 = new Pet();
        
        Propietario prop1 = new Propietario();
        prop1.setNome("Augusto");
        List<Endereco> listaEnderecos1 = prop1.getListaEnderecos();
        listaEnderecos1.add(e1);
        List<Pet> listaPets1 = prop1.getListaPets();
        listaPets1.add(p1);
    }
}
