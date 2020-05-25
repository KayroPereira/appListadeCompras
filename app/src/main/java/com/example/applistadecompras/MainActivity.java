package com.example.applistadecompras;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
        adapter.adicionar(new SaveProductActivity() , "Compartilhar");
        //adapter.adicionar(new frag_categoria(), "Lista");

        viewPager = (ViewPager) findViewById(R.id.vp1);
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

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

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    public void ivClickedCpDB(View item){
        if ((item instanceof ImageView) || (item instanceof TextView)) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //Log.println(Log.VERBOSE, "Teste", item.getTag().toString());
            fragmentTransaction.replace(R.id.frmLClear, new FragCategoria((int) item.getTag())).addToBackStack(null).commit();
        }
    }
}

