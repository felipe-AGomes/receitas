package com.felipeagomes.receitas.util;

public class EntityUpdateValidator {
    private EntityUpdateValidator() {}

    public static <T> boolean canUpdate(T oldParam, T newParam) {
        return !oldParam.equals(newParam) && newParam != null;
    }
}
