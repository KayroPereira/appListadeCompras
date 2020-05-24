package com.example.applistadecompras;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class tela1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public tela1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag1.
     */
    // TODO: Rename and change types and number of parameters
    public static tela1 newInstance(String param1, String param2) {
        tela1 fragment = new tela1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private Button btFrag2;
    private ArrayList<DBProduto> produtos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tela1, container, false);

        RecyclerView rv = view.findViewById(R.id.rv);
        //rv.setLayoutManager(new LinearLayoutManager(this));

        produtos = new DBProduto().createContactsList(20);

        //ProdutoAdapter adapter = new ProdutoAdapter(produtos, this);
        ProdutoAdapter adapter = new ProdutoAdapter(produtos, getContext());
        rv.setAdapter(adapter);
        //rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        /*

        View view = inflater.inflate(R.layout.fragment_frag1, container, false);

        btFrag2 = view.findViewById(R.id.btFrag2);

        btFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                //FragmentManager fragmentManager = getFragmentManager();
                //FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                //FragmentManager fragmentManager = getChildFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                //fragmentTransaction.remove(new Frag1());
                //fragmentTransaction.hide(this.);

                //fragmentTransaction.add(R.id.frmL1, new Frag4());
                //fragmentTransaction.commit();


                fragmentTransaction.replace(R.id.frmLClear, new Frag4()).addToBackStack(null).commit();


                //fragmentTransaction.replace(R.id.frmL1, new Frag4()).commit();
            }
        });

        //return inflater.inflate(R.layout.fragment_frag1, container, false);

         */
        return view;
    }
}

/*

public class tela1 extends Fragment {

    private ArrayList<DBProduto> produtos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tela1, container, false);

        RecyclerView rv = view.findViewById(R.id.rv);
        //rv.setLayoutManager(new LinearLayoutManager(this));

        produtos = new DBProduto().createContactsList(20);

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
/*
        return view;
    }
}

 */