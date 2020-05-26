package com.example.applistadecompras;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.applistadecompras.Communication.CommFirebase;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<DBProduto> produtosLista;
    private TabLayout tabLayout;

    private int[] tabIcons = {
            R.drawable.none_day,
            R.drawable.rain,
            R.drawable.none_night
    };

    private ViewPager viewPager;
    private AbasAdapter adapter;
    private int page = 0;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();

    final DatabaseReference dbOutStatus = reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new AbasAdapter(getSupportFragmentManager());
        adapter.adicionar(new tela1(), "Lista");
        //adapter.adicionar(new DashBoardCategoria(), "Menu");
        adapter.adicionar(new FragClear(), "Despensa");
        adapter.adicionar(new FragClearOther() , "Compartilhar");
        //adapter.adicionar(new frag_categoria(), "Lista");

        viewPager = (ViewPager) findViewById(R.id.vp1);
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Log.println(Log.VERBOSE, "Teste: ", position+" t1");
            }

            @Override
            public void onPageSelected(int position) {
                //Log.println(Log.VERBOSE, "Teste: ", position+" t2");
                page = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //Log.println(Log.VERBOSE, "Teste: ", state+" t3");
            }
        });

        dbOutStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                produtosLista = new CommFirebase().getListaCompras(dataSnapshot, new ConstantsApp().getPathDespensa());

                //new ProdutoDAO(getApplicationContext()).saveList(produtosLista);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

/*
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        //rv.setLayoutManager(new LinearLayoutManager(this));

        produtos = new Produto().createContactsList(20);

        ProdutoAdapter adapter1 = new ProdutoAdapter(produtos, this);
        rv.setAdapter(adapter1);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //rv.setItemViewCacheSize(produtos.size());
 */

    }
//sika fill rápido teto frio
    @Override
    public void onBackPressed() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        for (Fragment temp : fragmentManager.getFragments()){
           // assert temp.getTag() != null;
            //if (!temp.getTag().isEmpty()){
            if (temp.getTag() != null){
                String frag = temp.getTag().replace("frag", "");

                try{
                    int fragNumber = Integer.parseInt(frag);
                    if (fragNumber == page) {
                        callFragmentDefault(fragNumber);
                        Log.println(Log.VERBOSE, "Teste: ", "Page: " + fragNumber);
                        return;
                    }
                }catch (Exception e){
                    Log.println(Log.VERBOSE, "Teste: ", "Page: " + "invalida");
                }
            }
        }

        super.onBackPressed();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    /*
    public void ivClickedCpDB(View item){
        if ((item instanceof ImageView) || (item instanceof TextView)) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frmLClear, new FragCategoria((int) item.getTag())).addToBackStack(null).commit();
        }
    }*/

    public void ivClickedCpDB_S(View item){
        if ((item instanceof ImageView) || (item instanceof TextView)) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //fragmentTransaction.replace(R.id.frmLClearOther, new FragSaveProduct((int) item.getTag())).addToBackStack(null).commit();
            //fragmentTransaction.replace(R.id.frmLClearOther, new FragSaveProduct((int) item.getTag())).commit();
            fragmentTransaction.replace(R.id.frmLClearOther, new FragSaveProduct((int) item.getTag())).addToBackStack("DashBoardCategoriaSave").commit();
        }
    }

    public void callFragmentDefault(int fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (fragment){
            case 1:
                fragmentTransaction.replace(R.id.frmLClear, new DashBoardCategoria()).commit();
                //fragmentTransaction.replace(R.id.frmLClear, new FragCategoria((int) item.getTag()), "frag1").addToBackStack(null).commit();
                //fragmentTransaction.replace(R.id.frmLClear, new FragCategoria((int) item.getTag()), "page1").addToBackStack(null).commit();
                break;

            case 2:
                //fragmentTransaction.replace(R.id.frmLClearOther, new FragSaveProduct((int) item.getTag())).commit();
                fragmentTransaction.replace(R.id.frmLClearOther, new DashBoardCategoria()).commit();
                //fragmentTransaction.replace(R.id.frmLClearOther, new FragSaveProduct((int) item.getTag()), "page2").addToBackStack(null).commit();
                break;
        }
    }

    public void ivClickedCpDB(View item){
        //DashBoardCategoria dsb = (DashBoardCategoria) getSupportFragmentManager().findFragmentByTag("0");
        //FragClear dsb = (FragClear) getSupportFragmentManager().findFragmentByTag("10");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if ((item instanceof ImageView) || (item instanceof TextView)) {
            //fragmentTransaction.replace(R.id.frmLClear, new FragCategoria((int) item.getTag())).addToBackStack(null).commit();
            //fragmentTransaction.replace(R.id.frmLClear, new FragCategoria((int) item.getTag())).commit();

            //fragmentTransaction.replace(R.id.frmLClear, new FragCategoria((int) item.getTag())).addToBackStack("DashBoardCategoria").commit();

            switch (page){
                case 1:
                    //fragmentTransaction.replace(R.id.frmLClear, new DashBoardCategoria()).commit();
                    //fragmentTransaction.replace(R.id.frmLClear, new FragCategoria((int) item.getTag()), "page1").addToBackStack(null).commit();

                    //fragmentTransaction.replace(R.id.frmLClear, new FragCategoria((int) item.getTag()), "frag" + page).addToBackStack(null).commit();
                    fragmentTransaction.replace(R.id.frmLClear, new FragCategoria((int) item.getTag()), "frag" + page).commit();
                    break;

                case 2:
                    //fragmentTransaction.replace(R.id.frmLClearOther, new FragSaveProduct((int) item.getTag())).commit();
                    //fragmentTransaction.replace(R.id.frmLClearOther, new DashBoardCategoria()).commit();
                    //fragmentTransaction.replace(R.id.frmLClearOther, new FragSaveProduct((int) item.getTag()), "page2").addToBackStack(null).commit();


                    fragmentTransaction.replace(R.id.frmLClearOther, new FragSaveProduct((int) item.getTag()), "frag" + page).commit();
                    break;
            }
        }
    }
}

