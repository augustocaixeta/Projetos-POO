package com.aacs.pessoa;

public class PessoaJuridica extends Pessoa {
    @Override
    public boolean isDocumentoValido() {
        return getIdentificacao()
                .replaceAll("[^0-9]", "") // Regex
                .length() == 14;
    }
}
