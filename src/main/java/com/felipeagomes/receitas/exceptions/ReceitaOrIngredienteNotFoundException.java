package com.felipeagomes.receitas.exceptions;

public class ReceitaOrIngredienteNotFoundException extends ReceitasException {
    public ReceitaOrIngredienteNotFoundException() {
        super("Receita ou ingrediente não encontrados");
    }
}
