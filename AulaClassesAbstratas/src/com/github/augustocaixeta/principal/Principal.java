package com.github.augustocaixeta.principal;

import com.github.augustocaixeta.classes.Conta;
import com.github.augustocaixeta.classes.ContaPoupanca;

public class Principal {
    public static void main(String[] args) {
        Conta cp = new ContaPoupanca();
        cp.setSaldo(1000.0);
        cp.imprimirExtrato();
    }
}
