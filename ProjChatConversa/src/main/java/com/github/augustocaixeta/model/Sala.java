package com.github.augustocaixeta.model;

import java.time.LocalDateTime;

public class Sala {
    private int id;
    private Usuario usuario;
    private Conversa conversa;
    private boolean administrador;
    private boolean saiu;
    private LocalDateTime dataEntrada;

    public int getId() {
        return id;
    }

    public void setId(int salaId) {
        this.id = id;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Conversa getConversa() {
        return conversa;
    }

    public void setConversa(Conversa conversa) {
        this.conversa = conversa;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public boolean isSaiu() {
        return saiu;
    }

    public void setSaiu(boolean saiu) {
        this.saiu = saiu;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }
}
