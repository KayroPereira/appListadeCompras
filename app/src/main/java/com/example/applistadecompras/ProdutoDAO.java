package com.example.applistadecompras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private final String TABLE_PRODUTO = "Produto";
    private DbGateway gw;

    public ProdutoDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);
    }

    public boolean saveList(ArrayList<DBProduto> list){

        try{
            for (DBProduto temp : list)
            saveItem(temp.getCategoria(), temp.getNome(), temp.getQuantidade(), temp.getUnidade(), temp.isStatus());

        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean saveItem(int categoria, String nome, float quantidade, int unidade, boolean status){
        ContentValues cv = new ContentValues();
        cv.put("Categoria", categoria);
        cv.put("Nome", nome);
        cv.put("Quantidade", quantidade);
        cv.put("Unidade", unidade);
        cv.put("Status", status ? 1 : 0);
        return gw.getDatabase().insert(TABLE_PRODUTO, null, cv) > 0;
    }

    public List<DBProduto> retornarTodos(){
        List<DBProduto> produtos = new ArrayList<>();
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Produto", null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("ID"));
            int categoria = cursor.getInt(cursor.getColumnIndex("Categoria"));
            String nome = cursor.getString(cursor.getColumnIndex("Nome"));
            float quantidade = cursor.getFloat(cursor.getColumnIndex("Quantidade"));
            int unidade = cursor.getInt(cursor.getColumnIndex("Unidade"));
            boolean status = cursor.getInt(cursor.getColumnIndex("Status")) > 0;
            produtos.add(new DBProduto(id, categoria, nome, quantidade, unidade, status));
        }
        cursor.close();
        return produtos;
    }

    public List<DBProduto> getListCategory(int category){
        List<DBProduto> produtos = new ArrayList<>();

        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Produto where Categoria = ?", new String[]{category+""}, null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("ID"));
            int categoria = cursor.getInt(cursor.getColumnIndex("Categoria"));
            String nome = cursor.getString(cursor.getColumnIndex("Nome"));
            float quantidade = cursor.getFloat(cursor.getColumnIndex("Quantidade"));
            int unidade = cursor.getInt(cursor.getColumnIndex("Unidade"));
            boolean status = cursor.getInt(cursor.getColumnIndex("Status")) > 0;
            produtos.add(new DBProduto(id, categoria, nome, quantidade, unidade, status));
        }
        cursor.close();
        return produtos;
    }
}