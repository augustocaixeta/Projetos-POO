package com.github.augustocaixeta.classes;

import java.text.SimpleDateFormat;

public class ContaPoupanca extends Conta {
    @Override
    public void imprimirExtrato() {
        System.out.println("");
        SimpleDateFormat time = new SimpleDateFormat("dd/MM/aaaa HH:mm:ss");
    }
}
