package com.github.augustocaixeta.classes;

import com.github.augustocaixeta.interfaces.IConta;

public class ContaPoupanca implements IConta {
    private double saldo;
    
    @Override
    public void depositar(double valor) {
        this.saldo += valor;
    }

    @Override
    public void sacar(double valor) {
        this.saldo -= valor;
    }

    @Override
    public double getSaldo() {
        return this.saldo;
    }
}
