package com.example.applistadecompras;

public class ConstantsApp {

    final int statusOn = 1;
    final int statusOff = 0;
    final int statusWait = 2;

    final int rangeRandom = 10000;

    final String nameCategory[] = {"Mercado", "Lanches", "Bebidas", "Frios", "Limpeza", "Casa", "Carnes", "Peixes", "Frutas e Verduras", "Temperos", "Pets", "Outros"};

    final String nameUnidade[] = {"un", "ml", "Kg"};

    final String pathDespensa = "lstCompras/despensa";

    final String pathMinhaLista = "lstCompras/minhaLst";

    final String pathFlgDsp = "/flgDsp";

    final int flgDsp = 1;
    final int flgMlst = 2;

    final String pathFlgMlst = "/flgMlst";

    public int getFlgDsp() {
        return flgDsp;
    }

    public int getFlgMlst() {
        return flgMlst;
    }

    public String getPathFlgDsp() {
        return pathFlgDsp;
    }

    public String[] getNameUnidade() {
        return nameUnidade;
    }

    public String getPathFlgMlst() {
        return pathFlgMlst;
    }

    private DBProduto saveProduto = new DBProduto();

    public DBProduto getSaveProduto() {
        return saveProduto;
    }

    public void setSaveProduto(DBProduto saveProduto) {
        this.saveProduto = saveProduto;
    }

    public int getRangeRandom() {
        return rangeRandom;
    }

    public int getStatusOn() {
        return statusOn;
    }

    public int getStatusOff() {
        return statusOff;
    }

    public int getStatusWait() {
        return statusWait;
    }

    public String getPathMinhaLista() {
        return pathMinhaLista;
    }

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
