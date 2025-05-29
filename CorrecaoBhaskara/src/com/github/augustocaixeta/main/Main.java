package com.github.augustocaixeta.main;

import com.github.augustocaixeta.funcionalidades.ExecutarCalculos;
import com.github.augustocaixeta.objetos.DadosEntrada;
import com.github.augustocaixeta.objetos.DadosSaida;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DadosEntrada de = new DadosEntrada();
        de.setA(sc.nextFloat());
        de.setB(sc.nextFloat());
        de.setC(sc.nextFloat());

        ExecutarCalculos ec = new ExecutarCalculos();
        DadosSaida ds = new DadosSaida();
        
        ds = ec.calcularDelta(de, ds);
        ds = ec.calcularX1L(de, ds);
        ds = ec.calcularX2L(de, ds);
        ds = ec.calcularVx(de, ds);
        ds = ec.calcularVy(de, ds);
        
        System.out.println("Delta: " + ds.getDelta());
        System.out.println("X1: " + ds.getX1L());
        System.out.println("X2: " + ds.getX2L());
        System.out.println("X vertice: " + ds.getVx());
        System.out.println("Y vertice: " + ds.getVy());
    }
}