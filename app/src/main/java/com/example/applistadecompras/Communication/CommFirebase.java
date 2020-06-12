package com.example.applistadecompras.Communication;

import com.example.applistadecompras.ConstantsApp;
import com.example.applistadecompras.DBProduto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class CommFirebase {

    private ConstantsApp constant = new ConstantsApp();

    public int getComponentStatus(DataSnapshot dataSnapshot, String path, String component){
        int valueComponent = -1;

        try {
            valueComponent = Integer.parseInt(dataSnapshot.child(path).child(component).getValue().toString());
        }catch (Exception e){

        }
        return valueComponent;
    }

    public String getItem(DataSnapshot dataSnapshot, String mode) {

        String item;
        try {
            DataSnapshot path = dataSnapshot.child(mode);

            item = path.getValue().toString();
        }catch (Exception e){
            return "";
        }
        return item;
    }

    public ArrayList<DBProduto> getListaCompras(DataSnapshot dataSnapshot, String path, int mode) {

        ArrayList<DBProduto> list = new ArrayList<>();

        try {
            DataSnapshot dataList = dataSnapshot.child(path);
            for (DataSnapshot temp : dataList.getChildren()) {
                for (DataSnapshot temp1 : temp.getChildren()) {
                    if (mode == constant.getFlgDsp())
                        list.add(new DBProduto(-1, Integer.parseInt(temp.getKey()), temp1.getKey(), (float) 1.0, Integer.parseInt(temp1.getValue().toString()), constant.getStatusOn()));
                    else{
                        String value[] = temp1.getValue().toString().split("#");
                        list.add(new DBProduto(-1, Integer.parseInt(temp.getKey()), temp1.getKey(), Float.parseFloat(value[0]), Integer.parseInt(value[1]), Integer.parseInt(value[2])));
                    }
                }
            }
        }catch (Exception e){
            return null;
        }
        return list;
    }

    public void sendDataInt(DatabaseReference reference, String path, int value){
        reference.child(path).setValue(value);
    }

    public void sendDataString(DatabaseReference reference, String path, String value){
        reference.child(path).setValue(value);
    }

    public void deleteItem(DatabaseReference reference, String path){
        reference.child(path).removeValue();
    }
}