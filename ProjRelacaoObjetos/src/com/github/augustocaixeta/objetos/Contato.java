package com.github.augustocaixeta.objetos;

public class Contato {
    private int contatoId;
    private String tipoContato;
    private String numeroContato;
    private Pessoa pessoa;
    
    public int getContatoId() {
        return contatoId;
    }

    public void setContatoId(int contatoId) {
        this.contatoId = contatoId;
    }
    
    public String getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(String tipoContato) {
        this.tipoContato = tipoContato;
    }

    public String getNumeroContato() {
        return numeroContato;
    }

    public void setNumeroContato(String numeroContato) {
        this.numeroContato = numeroContato;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
