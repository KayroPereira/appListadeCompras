package com.example.applistadecompras;

import java.io.Serializable;
import java.util.ArrayList;

public class DBProduto implements Serializable {

    private int id;
    private int Categoria;
    private String Nome;
    private float Quantidade;
    private int Unidade;
    private boolean Status;

    public DBProduto(){

    }

    public DBProduto(int id, int categoria, String nome, float quantidade, int unidade, boolean status) {
        this.id = id;
        Categoria = categoria;
        Nome = nome;
        Quantidade = quantidade;
        Unidade = unidade;
        Status = status;
    }

    public int getId() {
        return id;
    }

    public int getCategoria() {
        return Categoria;
    }

    public String getNome() {
        return Nome;
    }

    public float getQuantidade() {
        return Quantidade;
    }

    public int getUnidade() {
        return Unidade;
    }

    public boolean isStatus() {
        return Status;
    }

    @Override
    public boolean equals(Object o){
        return this.id == ((DBProduto)o).id;
    }

    @Override
    public int hashCode(){
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategoria(int categoria) {
        Categoria = categoria;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public void setQuantidade(float quantidade) {
        Quantidade = quantidade;
    }

    public void setUnidade(int unidade) {
        Unidade = unidade;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    //remover
    private int lastContactId = 0;
    public ArrayList<DBProduto> createContactsList(int numContacts) {
        ArrayList<DBProduto> produtos = new ArrayList<>();

        for (int i = 1; i <= numContacts; i++) {
            //produtos.add(new Produto("Produto " + ++lastContactId, 1,i % 3));
            //produtos.add(new Produto("Produto " + ++lastContactId, i % 3,i % 3));
            produtos.add(new DBProduto(i, 1, "Produto " + ++lastContactId, i-1,i % 3, i % 2 == 0 ? true : false));
        }
        return produtos;
    }
}