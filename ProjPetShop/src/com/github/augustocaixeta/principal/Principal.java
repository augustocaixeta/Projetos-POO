package com.github.augustocaixeta.principal;

import com.github.augustocaixeta.classes.Endereco;
import com.github.augustocaixeta.classes.Pet;
import com.github.augustocaixeta.classes.Propietario;
import com.github.augustocaixeta.exibicao.Exibicao;

public class Principal {
    public static void main(String[] args) {
        Propietario p1 = new Propietario("AUGUSTO A.", "111.222.333-44", "(34) 9 1111-2222", "augusto@gmail.com");
        Propietario p2 = new Propietario("BRUNO C.", "222.333.444-66", "(34) 9 2222-3333", "bruno@gmail.com");
        Propietario p3 = new Propietario("DIEGO J.", "333.444.555-77", "(34) 9 4444-5555", "diego@gmail.com");
        
        Endereco e1 = new Endereco("Alemanha", 100, "Nacoes", "Patrocinio", "38745-150", p1);
        Endereco e2 = new Endereco("Porto Rico", 205, "Nacoes", "Patrocinio", "38745-145", p2);
        Endereco e3 = new Endereco("Cuba", 64, "Nacoes", "Patrocinio", "38745-140", p3);
        Endereco e4 = new Endereco("Argentina", 180, "Nacoes", "Patrocinio", "38745-155", p1);
        
        Pet pet1 = new Pet("Rex", 5, "Cachorro", "Labrador", 30.5F, p1);
        Pet pet2 = new Pet("Mimi", 2, "Gato", "Persa", 4.2F, p1);
        Pet pet3 = new Pet("Bolinha", 1, "Cachorro", "Bulldog", 8.5F, p1);
        Pet pet4 = new Pet("Luna", 3, "Gato", "Ragdoll", 4.5F, p2);
        Pet pet5 = new Pet("Bob", 4, "Cachorro", "Poodle", 4.5F, p3);
        
        p1.adicionarEndereco(e1);
        p1.adicionarEndereco(e4);
        p2.adicionarEndereco(e2);
        p3.adicionarEndereco(e3);

        p1.adicionarPet(pet1);
        p1.adicionarPet(pet2);
        p1.adicionarPet(pet3);
        p2.adicionarPet(pet4);
        p3.adicionarPet(pet5);
        
        Exibicao e = new Exibicao();
        e.exibirDadosPropietario(p1);
        e.exibirDadosPropietario(p2);
        e.exibirDadosPropietario(p3);
    }
}