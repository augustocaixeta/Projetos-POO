package com.github.augustocaixeta.classes;

import com.github.augustocaixeta.interfaces.IConta;

public class ContaCorrente implements IConta {
    private double saldo;
    private final double taxaOperacao = 0.45;
    
    @Override
    public void depositar(double valor) {
        this.saldo += valor - taxaOperacao;
    }

    @Override
    public void sacar(double valor) {
        this.saldo -= valor + taxaOperacao;
    }

    @Override
    public double getSaldo() {
        return this.saldo;
    }
}
