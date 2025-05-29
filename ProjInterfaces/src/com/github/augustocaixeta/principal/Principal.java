package com.github.augustocaixeta.principal;

import com.github.augustocaixeta.classes.ContaCorrente;
import com.github.augustocaixeta.classes.ContaPoupanca;
import com.github.augustocaixeta.classes.GerarExtratos;

public class Principal {
    public static void main(String[] args) {
        ContaCorrente cc = new ContaCorrente();
        cc.depositar(1300);
        cc.sacar(300);
        
        ContaPoupanca cp = new ContaPoupanca();
        cp.depositar(900);
        cp.sacar(25);
        
        GerarExtratos gerador = new GerarExtratos();
        gerador.geradorExtratoConta(cc);
        gerador.geradorExtratoConta(cp);
    }
}
