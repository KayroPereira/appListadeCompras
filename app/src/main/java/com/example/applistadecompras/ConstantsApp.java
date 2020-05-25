package com.example.applistadecompras;

public class ConstantsApp {

    final String nameCategory[] = {"Mercado", "Lanches", "Bebidas", "Frios", "Limpeza", "Casa", "Carnes", "Pets", "Frutas e Verduras", "Temperos", "Temporários", "Outros"};

    final String pathDespensa = "lstCompras/despensa";

    public String getPathDespensa() {
        return pathDespensa;
    }

    public String[] getNameCategory() {
        return nameCategory;
    }

    public String getNameCategoryItem(int item) {
        return nameCategory[item];
    }
}
