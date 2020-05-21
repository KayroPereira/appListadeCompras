package com.example.applistadecompras;

import java.util.ArrayList;

public class Produto {
    private String mName;
    private float quantidade;
    private int unidade;

    public Produto(){

    }

    public Produto(float quantidade, int unidade) {
        this.quantidade = quantidade;
        this.unidade = unidade;
    }

    public Produto(String mName, float quantidade, int unidade) {
        this.mName = mName;
        this.quantidade = quantidade;
        this.unidade = unidade;
    }

    public String getmName() {
        return mName;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public int getUnidade() {
        return unidade;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public void setUnidade(int unidade) {
        this.unidade = unidade;
    }

    private int lastContactId = 0;
    public ArrayList<Produto> createContactsList(int numContacts) {
        ArrayList<Produto> produtos = new ArrayList<Produto>();

        for (int i = 1; i <= numContacts; i++) {
            //produtos.add(new Produto("Produto " + ++lastContactId, 1,i % 3));
            //produtos.add(new Produto("Produto " + ++lastContactId, i % 3,i % 3));
            produtos.add(new Produto("Produto " + ++lastContactId, i-1,i % 3));
        }
        return produtos;
    }
}
