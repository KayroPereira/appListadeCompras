package com.example.applistadecompras;

import java.io.Serializable;

public class DBProduto implements Serializable {

    private int id;
    private int Categoria;
    private String Nome;
    private float Quantidade;
    private int Unidade;
    private boolean Status;

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
}
