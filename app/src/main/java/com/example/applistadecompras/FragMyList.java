package com.example.applistadecompras;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applistadecompras.Communication.CommFirebase;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FragMyList extends Fragment implements Callbacks, OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragMyList() {
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
    public static FragMyList newInstance(String param1, String param2) {
        FragMyList fragment = new FragMyList();
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

    private List<DBProduto> produtos;
    private ConstantsApp constants = new ConstantsApp();
    private boolean purchasedProductList = false;

    private RecyclerView rv;
    private View view;
    private ImageView ivClearBasket;
    private ImageView ivClearList;
    private TextView tvClearBasket;
    private TextView tvClearList;
    //private MyListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //rv.setLayoutManager(new LinearLayoutManager(this));

        //produtos = new DBProduto().createContactsList(20);

        view = inflater.inflate(R.layout.frag_my_list, container, false);

        ivClearBasket = view.findViewById(R.id.ivClearBasketMYL);
        ivClearList = view.findViewById(R.id.ivClearListMYL);
        tvClearBasket = view.findViewById(R.id.tvClearBasketMYL);
        tvClearList = view.findViewById(R.id.tvClearListMYL);

        //view.setOnClickListener(this);
        ivClearBasket.setOnClickListener(this);
        ivClearList.setOnClickListener(this);
        tvClearBasket.setOnClickListener(this);
        tvClearList.setOnClickListener(this);

        updateMyList();
        /*
        produtos = new ProdutoDAO(getContext()).getListProduct(3, -1);

        //ProdutoAdapter adapter = new ProdutoAdapter(produtos, this);
        //ProdutoAdapter adapter = new ProdutoAdapter(produtos, getContext());

        MyListAdapter adapter = new MyListAdapter(produtos, getContext());
        rv.setAdapter(adapter);
        //rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        */

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

    @Override
    public void updateMyList() {
        produtos = new ProdutoDAO(getContext()).getListProduct(3, -1);
        purchasedProductList = false;

        List<DBProduto> tempProduto = new ArrayList<>();
        List<DBProduto> purchasedProducts = new ArrayList<>();

        purchasedProducts.add(new DBProduto(-1, -1, getString(R.string.cestaOk), 0, -1, -1));

        //int id, int categoria, String nome, float quantidade, int unidade, int status
        int category = -1,
            position = 0;
        for (DBProduto temp : produtos){
            if (temp.getCategoria() != category){
                category = temp.getCategoria();

                for (int i = position; (i < produtos.size() && category == produtos.get(i).getCategoria()); i++){
                    if (produtos.get(i).getStatus() == constants.getStatusWait()){
                        tempProduto.add(new DBProduto(-1, temp.getCategoria(), constants.getNameCategoryItem(temp.getCategoria()), 0, -1, -1));
                        break;
                    }
                }
            }

            if (temp.getStatus() != constants.getStatusOff())
                tempProduto.add(temp);
            else {
                temp.setId(-2);
                purchasedProducts.add(temp);
            }
            position++;
        }

        produtos = new ArrayList<>(tempProduto);
        if (purchasedProducts.size() > 1) {
            produtos.addAll(purchasedProducts);
            purchasedProductList = true;
        }

        if (produtos.size() == 0){
            produtos.add(new DBProduto(-2, -2, getString(R.string.listClear), 0, -1, -1));
        }

        //ProdutoAdapter adapter = new ProdutoAdapter(produtos, this);
        //ProdutoAdapter adapter = new ProdutoAdapter(produtos, getContext());

        rv = view.findViewById(R.id.rv);

        MyListAdapter adapter = new MyListAdapter(produtos, getContext());
        rv.setAdapter(adapter);
        //rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onClick(View view) {
        DatabaseReference dbOutStatus = ((MainActivity) getContext()).getDbOutStatus();
        String path = constants.getPathMinhaLista();
        String eventClick = view.getTag().toString();

        if (produtos.size() == 0) {
            Snackbar.make(view, getString(R.string.msgEmptList) + getString(R.string.msgLista) + ".", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            return;
        }

        if (eventClick.equals("clearBasket") && !purchasedProductList) {
            Snackbar.make(view, getString(R.string.msgEmptList) + getString(R.string.msgCesta) + ".", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            return;
        }

        if (eventClick.equals("clearBasket") || eventClick.equals("clearList")) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setMessage(getString(R.string.msgClearProduct) + " " + (eventClick.equals("clearBasket") ? getString(R.string.msgCesta) : getString(R.string.msgLista)) + "?");
            dialog.setPositiveButton(getString(R.string.sim), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface di, int arg) {
                    boolean updateFlag = false;

                    if (eventClick.equals("clearBasket")) {
                        for (DBProduto temp : produtos) {
                            if (temp.getStatus() == constants.getStatusOff()) {
                                new CommFirebase().deleteItem(dbOutStatus, path + "/" + temp.getCategoria() + "/" + temp.getNome());
                                updateFlag = true;
                            }
                        }
                    } else {
                        int category = -1;
                        for (DBProduto temp : produtos) {
                            if (temp.getCategoria() != category) {
                                new CommFirebase().deleteItem(dbOutStatus, path + "/" + temp.getCategoria());
                                category = temp.getCategoria();
                            }
                        }
                        updateFlag = true;
                    }

                    if (updateFlag)
                        new CommFirebase().sendDataInt(dbOutStatus, path + constants.getPathFlgMlst(), new Random().nextInt(constants.rangeRandom));
                }
            });
            dialog.setNegativeButton(getString(R.string.nao), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface di, int arg) {
                }
            });
            dialog.setTitle(eventClick.equals("clearBasket") ? getString(R.string.clearBasket) : getString(R.string.clearList));
            dialog.show();
        }
    }
}

/*

public class FragMyList extends Fragment {

    private ArrayList<DBProduto> produtos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.FragMyList, container, false);

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
        View view = inflater.inflate(R.layout.FragMyList, container, false);

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