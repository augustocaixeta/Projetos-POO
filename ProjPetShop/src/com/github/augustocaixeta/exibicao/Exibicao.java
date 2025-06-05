package com.github.augustocaixeta.exibicao;

import com.github.augustocaixeta.classes.Endereco;
import com.github.augustocaixeta.classes.Pet;
import com.github.augustocaixeta.classes.Propietario;

public class Exibicao {
    public void exibirDadosPropietario(Propietario propietario) {
        System.out.println("# Propietario:\n" + propietario.toString()
                + "\n\n# Enderecos:");
        
        for (Endereco e : propietario.getListaEnderecos()) {
            System.out.println(e.toString());
        }
        
        System.out.println("\n# Pets:");
        for (Pet p : propietario.getListaPets()) {
            System.out.println(p.toString());
        }
    }
}
