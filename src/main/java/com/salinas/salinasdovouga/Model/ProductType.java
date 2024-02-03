package com.salinas.salinasdovouga.Model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public enum ProductType implements Serializable {
    SAL("Sal"),
    FLOR_DE_SAL("Flor de Sal");

    private final String displayName;

    ProductType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static List<String> getDisplayNames() {
        return Arrays.asList(SAL.displayName, FLOR_DE_SAL.displayName);
    }
}