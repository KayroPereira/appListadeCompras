package com.example.applistadecompras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private final String TABLE_PRODUTO = "Produto";
    private final String TABLE_UP_PRODUTO = "UpProduto";

    private DbGateway gw;

    public boolean deleteProduto(){
        return gw.getDatabase().delete(TABLE_PRODUTO, "", new String[]{}) > 0;
    }

    public boolean updateFlagProduto(int flag){
        ContentValues cv = new ContentValues();
        cv.put("upFlag", flag);
        //return gw.getDatabase().update(TABLE_UP_PRODUTO, cv, "upFlag=1", new String[]{1 + ""}) > 0;
        return gw.getDatabase().update(TABLE_UP_PRODUTO, cv, "", new String[]{}) > 0;
    }

    public boolean inputFlagProduto(int flag){
        ContentValues cv = new ContentValues();
        cv.put("upFlag", flag);
        return gw.getDatabase().insert(TABLE_UP_PRODUTO, null, cv) > 0;
    }

    public int getFlagProduto(){
        int upFlag = -1;

        try {
            Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM " + TABLE_UP_PRODUTO, null);
            while (cursor.moveToNext()) {
                upFlag = cursor.getInt(cursor.getColumnIndex("upFlag"));
            }
            cursor.close();
        }catch (Exception e){
            Log.println(Log.VERBOSE, "Teste: ", " Ex: " + e);
        }
        return upFlag;
    }

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