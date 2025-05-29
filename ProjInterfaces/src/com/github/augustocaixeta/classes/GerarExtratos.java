package com.github.augustocaixeta.classes;

import com.github.augustocaixeta.interfaces.IConta;

public class GerarExtratos {
    public void geradorExtratoConta(IConta conta) {
        System.out.println("Saldo atual: " + conta.getSaldo());
    }
}
