package com.aacs.exercicios_cicero;

public class Bhaskara {
    public static BhaskaraResult calculate(double a, double b, double c) {
        double delta = b * b - 4 * a * c;

        if (delta < 0) {
            return null;
        }

        double raiz1 = (-b + Math.sqrt(delta)) / (2 * a);
        double raiz2 = (-b - Math.sqrt(delta)) / (2 * a);
        double verticeX = (raiz1 + raiz2) / 2.0;
        double verticeY = -delta / (4 * a);
        
        return new BhaskaraResult(raiz1, raiz2, verticeX, verticeY);
    }
}
