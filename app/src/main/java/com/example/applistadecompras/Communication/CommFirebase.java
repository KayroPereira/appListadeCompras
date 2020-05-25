package com.example.applistadecompras.Communication;

import com.example.applistadecompras.DBProduto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class CommFirebase {

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

    public ArrayList<DBProduto> getListaCompras(DataSnapshot dataSnapshot, String mode) {

        ArrayList<DBProduto> list = new ArrayList<>();

        try {
            DataSnapshot path = dataSnapshot.child(mode);
            for (DataSnapshot temp : path.getChildren()) {
                for (DataSnapshot temp1 : temp.getChildren()) {
                    list.add(new DBProduto(-1, Integer.parseInt(temp.getKey()), temp1.getKey(), (float) 1.0, Integer.parseInt(temp1.getValue().toString()), true));
                }
            }
        }catch (Exception e){
            return null;
        }
        return list;
    }

    public void sendDataInt(DatabaseReference reference, String path, int value){
        //dbOutStatus.child("living").child("power").child("out4").setValue(action ? 1 : 0);
        //dbOutStatus.child("kitchen/l/o1").setValue(8);
        reference.child(path).setValue(value);
    }

    public void sendDataString(DatabaseReference reference, String path, String value){
        reference.child(path).setValue(value);
    }
}
