package com.example.applistadecompras;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Produto> produtos;
    private TabLayout tabLayout;

    private int[] tabIcons = {
            R.drawable.none_day,
            R.drawable.rain,
            R.drawable.none_night
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AbasAdapter adapter = new AbasAdapter( getSupportFragmentManager() );
        adapter.adicionar( new tela1() , "Primeira Aba");
        adapter.adicionar( new tela2(), "Segunda Aba");
        adapter.adicionar( new tela3(), "Terceira Aba");

        ViewPager viewPager = (ViewPager) findViewById(R.id.vp1);
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

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
}

