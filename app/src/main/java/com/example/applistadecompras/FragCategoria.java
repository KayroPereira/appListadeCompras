package com.example.applistadecompras;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FragCategoria extends Fragment implements Callbacks{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int categoria;

    private RecyclerView rv;
    private ProdutoDAO dao;

    public FragCategoria(int categoria) {
        this.categoria = categoria;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private ImageView btBack;

    private TextView tvCategoriaCTG;

    private List<DBProduto> produtos;
    ProdutoAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_categoria, container, false);

        btBack = view.findViewById(R.id.ivBackFRC);
        tvCategoriaCTG = view.findViewById(R.id.tvCategoriaCTG);
        rv = view.findViewById(R.id.rvT2);

        tvCategoriaCTG.setText(new ConstantsApp().getNameCategoryItem(categoria));

        ConstraintLayout clFRC_2 = (ConstraintLayout) view.findViewById(R.id.clFRC_2);
        clFRC_2.setBackgroundResource(R.drawable.gradient_2);

        dao = new ProdutoDAO(getContext());

        updateProducts();

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frmLClear, new DashBoardCategoria()).commit();
            }
        });

        return view;
    }

    @Override
    public void updateProducts() {
        produtos = dao.getListProduct(1, categoria);
        adapter = new ProdutoAdapter(produtos, getContext());
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }
}