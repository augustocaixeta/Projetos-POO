package com.github.augustocaixeta.interfaces;

public interface IConta {
    void depositar(double valor);
    void sacar(double valor);
    double getSaldo();
}
