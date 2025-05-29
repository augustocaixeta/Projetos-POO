package com.aacs.pessoa;

public abstract class Pessoa {
    String identificacao;

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }
    
    public abstract boolean isDocumentoValido();
}
