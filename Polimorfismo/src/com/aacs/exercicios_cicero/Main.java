package com.aacs.exercicios_cicero;

import static com.aacs.exercicios_cicero.Bhaskara.calculate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double c = sc.nextDouble();
        
        BhaskaraResult result = calculate(a, b, c);
        
        System.out.println("x1: " + result.raiz1 + "\n"
                    + "x2: " + result.raiz2 + "\n"
                    + "vertice X: " + result.verticeX + "\n"
                    + "vertice Y: " + result.verticeY);
    }
}
