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

//implementa interface para ProdutoAdapter
//public class FragCategoria extends Fragment implements ProdutoAdapter.ContactsAdapterListener{
public class FragCategoria extends Fragment implements Callbacks{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param //param1 Parameter 1.
     * @param //param2 Parameter 2.
     * @return A new instance of fragment Frag4.
     */
    /*
    // TODO: Rename and change types and number of parameters
    public static frag_categoria newInstance(String param1, String param2) {
        frag_categoria fragment = new frag_categoria();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

     */

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
        /*
        produtos = dao.getListProduct(1, categoria);

        adapter = new ProdutoAdapter(produtos, getContext(), FragCategoria.this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
         */

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getFragmentManager().popBackStack();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frmLClear, new DashBoardCategoria()).commit();
            }
        });

        return view;
    }

    /*
    //recebe informações do ProdutoAdapter
    @Override
    public void onContactSelected(DBProduto pd) {
        Toast.makeText(getContext(), "Call Back Ok: " + pd.getNome(), Toast.LENGTH_SHORT).show();
        updateList();
    }
    */

    @Override
    public void updateProducts() {
        //Toast.makeText(getContext(), "Call Back Ok: ", Toast.LENGTH_SHORT).show();

        produtos = dao.getListProduct(1, categoria);

        /*
        //Passa o fragment como parametro para ProdutoAdapter
        adapter = new ProdutoAdapter(produtos, getContext(), FragCategoria.this);
         */
        adapter = new ProdutoAdapter(produtos, getContext());
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }
}


/*
public class frag_categoria extends Fragment {

    private List<DBProduto> produtos;
    ProdutoAdapter adapter;

    private Button btBackT2;

    private FragmentManager fragmentTransaction;
    private DashBoardCategoria testeFragment;
    private FragmentTransaction transaction;

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(android.R.id.content, new MainFragment(), this.toString())
                    .commit();
        }
    }
     */

/*


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_categoria, container, false);

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

        fragmentTransaction = getFragmentManager();
        //fragmentTransaction = getActivity().getSupportFragmentManager();
        transaction = fragmentTransaction.beginTransaction();

        btBackT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //transaction.replace(R.id.clTela2_1, testeFragment);
                //transaction.commit();

                getFragmentManager().popBackStack();


                /*
                Toast.makeText(getActivity(),"TESTEBUTTON",Toast.LENGTH_SHORT).show();

                testeFragment = (DashBoardCategoria) fragmentTransaction.findFragmentById(R.id.clDashboard);

                if(testeFragment == null)
                    testeFragment = new DashBoardCategoria();


                transaction.replace(R.id.clTela2_1, testeFragment);
                //transaction.replace(R.id.clDashboard, testeFragment);

                transaction.addToBackStack(null);

                try {
                    this.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }

                transaction.commit();
                */

                //getActivity().getFragmentManager().popBackStack();
                //getActivity().onBackPressed();
                /*
                try {
                    //frag_categoria.this.finalize();
                    frag_categoria.this.onDestroy();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                 */

/*

            }
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        onDestroy();
    }
}

 */
