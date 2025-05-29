package com.github.augustocaixeta.funcionalidades;

import com.github.augustocaixeta.objetos.DadosEntrada;
import com.github.augustocaixeta.objetos.DadosSaida;

public class ExecutarCalculos {
    public DadosSaida calcularDelta(DadosEntrada de, DadosSaida ds) {
        ds.setDelta(de.getB() * de.getB() - 4 * de.getA() * de.getC());

        return ds;
    }

    public DadosSaida calcularX1L(DadosEntrada de, DadosSaida ds) {
        if (ds.getDelta() >= 0) {
            ds.setX1L((float) ((-de.getB() + Math.sqrt(ds.getDelta())) / (2 * de.getA())));
        }

        return ds;
    }

    public DadosSaida calcularX2L(DadosEntrada de, DadosSaida ds) {
        if (ds.getDelta() >= 0) {
            ds.setX2L((float) ((-de.getB() - Math.sqrt(ds.getDelta())) / (2 * de.getA())));
        }

        return ds;
    }

    public DadosSaida calcularVx(DadosEntrada de, DadosSaida ds) {
        ds.setVx(-de.getB() / (2 * de.getA()));
        
        return ds;
    }

    public DadosSaida calcularVy(DadosEntrada de, DadosSaida ds) {
        if (ds.getDelta() >= 0) {
            ds.setVy(-ds.getDelta() / 4 * de.getA());
        }
        
        return ds;
    }
}
