package com.example.applistadecompras;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class tela1 extends Fragment {

    private ArrayList<Produto> produtos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tela1, container, false);

        RecyclerView rv = view.findViewById(R.id.rv);
        //rv.setLayoutManager(new LinearLayoutManager(this));

        produtos = new Produto().createContactsList(20);

        //ProdutoAdapter adapter = new ProdutoAdapter(produtos, this);
        ProdutoAdapter adapter = new ProdutoAdapter(produtos, getContext());
        rv.setAdapter(adapter);
        //rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        //rv.setItemViewCacheSize(produtos.size());
        /*
        i = 0;
        View view = inflater.inflate(R.layout.tela1, container, false);

        TextView tv = view.findViewById(R.id.textView);
        tv.setText("Você está na primeira aba");

        tv1 = view.findViewById(R.id.textView2);

        Button bt = view.findViewById(R.id.button);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv1.setText("Cont: " + i++);
            }
        });
         */

        return view;
    }
}