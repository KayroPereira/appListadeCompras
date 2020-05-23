package com.example.applistadecompras;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class tela2 extends Fragment {

    private List<DBProduto> produtos;
    ProdutoAdapter adapter;

    private Button btBackT2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tela2, container, false);

        btBackT2 = view.findViewById(R.id.btBackT2);
        RecyclerView rv = view.findViewById(R.id.rvT2);

        ProdutoDAO dao = new ProdutoDAO(getContext());
        //adapter = new ProdutoAdapter(dao.retornarTodos());
        //recyclerView.setAdapter(adapter);

        //produtos = new DBProduto().createContactsList(20);

        produtos = dao.retornarTodos();

        adapter = new ProdutoAdapter(produtos, getContext());
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        btBackT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFragmentManager().popBackStack();
                //getActivity().getFragmentManager().popBackStack();
                //getActivity().onBackPressed();
                /*
                try {
                    //tela2.this.finalize();
                    tela2.this.onDestroy();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                 */
            }
        });

        return view;
    }
}
