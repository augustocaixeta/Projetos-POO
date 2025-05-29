package com.aacs.polimorfismo;

public class Aplicacao {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.som();
        
        Animal cachorro = new Cachorro();
        cachorro.som();
        
        Animal gato = new Gato();
        gato.som();
    }
}
