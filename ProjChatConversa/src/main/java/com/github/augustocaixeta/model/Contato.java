package com.github.augustocaixeta.model;

import java.time.LocalDateTime;

public class Contato {
    private int id;
    private Usuario titular;
    private Usuario contato;
    private LocalDateTime adicionadoEm;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Usuario getTitular() {
        return titular;
    }
    
    public void setTitular(Usuario titular) {
        this.titular = titular;
    }
    
    public Usuario getContato() {
        return contato;
    }
    
    public void setContato(Usuario contato) {
        this.contato = contato;
    }
    
    public LocalDateTime getAdicionadoEm() {
        return adicionadoEm;
    }
    
    public void setAdicionadoEm(LocalDateTime adicionadoEm) {
        this.adicionadoEm = adicionadoEm;
    }
}
