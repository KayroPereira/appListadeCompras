package com.example.applistadecompras;

import android.graphics.Color;
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
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ArrayList<DBProduto> produtosLista;

    private DBProduto sProduto = new DBProduto();

    private ConstantsApp constants = new ConstantsApp();

    private TabLayout tabLayout;

    private int[] tabIcons = {
            R.drawable.mylistoff,
            R.drawable.myliston,
            R.drawable.despensaoff,
            R.drawable.despensaon,
            R.drawable.opcaooff,
            R.drawable.opcaoon
    };

    private ViewPager viewPager;
    private AbasAdapter adapter;
    private Callbacks callbacks;
    private Callbacks callbacksProductCategoria;

    private ImageView ivTab0;
    private ImageView ivTab1;
    private ImageView ivTab2;

    private int page = 0;
    private boolean flgUpdateTab0 = false;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();

    final DatabaseReference dbOutStatus = reference;

    final int PAGER_0 = 0;
    final int PAGER_1 = 1;
    final int PAGER_2 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivTab0 = (ImageView) findViewById(R.id.ivTab0);
        ivTab1 = (ImageView) findViewById(R.id.ivTab1);
        ivTab2 = (ImageView) findViewById(R.id.ivTab2);

        //ivTab0.setOnClickListener();

        sProduto.setCategoria(-1);

        //callbacks = (Callbacks) new FragMyList();

        adapter = new AbasAdapter(getSupportFragmentManager());
        adapter.adicionar(new FragMyList(), "Minha Lista");
        //adapter.adicionar(new DashBoardCategoria(), "Menu");
        adapter.adicionar(new FragClear(), "Despensa");
        adapter.adicionar(new FragClearOther() , "Opções");
        //adapter.adicionar(new frag_categoria(), "Lista");

        viewPager = (ViewPager) findViewById(R.id.vp1);
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Log.println(Log.VERBOSE, "Teste: ", position+" t1");
            }

            @Override
            public void onPageSelected(int position) {
                //Log.println(Log.VERBOSE, "Teste: ", position+" t2");
                page = position;
                setupTabIcons(page);

                if (page == PAGER_0 && flgUpdateTab0) {
                    callbacks.updateMyList();
                    flgUpdateTab0 = false;
                }

                if (page == PAGER_1) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frmLClear, new DashBoardCategoria()).commit();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //Log.println(Log.VERBOSE, "Teste: ", state+" t3");
            }
        });

        dbOutStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int upFlag = new ProdutoDAO(getApplicationContext()).getFlagProduto(constants.getFlgDsp());

                if (upFlag == -1) {
                    new ProdutoDAO(getApplicationContext()).updateFlagProduto(-1, 0);
                    new ProdutoDAO(getApplicationContext()).updateFlagProduto(-1, 0);
                }

                int firebaseFlag = Integer.parseInt(new CommFirebase().getItem(dataSnapshot,constants.getPathDespensa()+constants.getPathFlgDsp()));

                /*
                if (upFlag == -1) {
                    new ProdutoDAO(getApplicationContext()).updateFlagProduto(-1, 0);
                }*/

                if (upFlag != firebaseFlag) {
                    produtosLista = new CommFirebase().getListaCompras(dataSnapshot, constants.getPathDespensa(), constants.getFlgDsp());
                    //new ProdutoDAO(getApplicationContext()).deleteProduto(constants.getFlgDsp());
                    new ProdutoDAO(getApplicationContext()).deleteProduto(-1);
                    new ProdutoDAO(getApplicationContext()).saveList(produtosLista);
                    new ProdutoDAO(getApplicationContext()).updateFlagProduto(constants.getFlgDsp(), firebaseFlag);
                    new ProdutoDAO(getApplicationContext()).updateFlagProduto(constants.getFlgMlst(), firebaseFlag);
                }

                //Log.println(Log.VERBOSE, "Teste: ", " Flag: " + upFlag);

                upFlag = new ProdutoDAO(getApplicationContext()).getFlagProduto(constants.getFlgMlst());
                firebaseFlag = Integer.parseInt(new CommFirebase().getItem(dataSnapshot,constants.getPathMinhaLista()+constants.getPathFlgMlst()));

                if (upFlag != firebaseFlag) {
                    ArrayList<DBProduto> produtosMyLista = new CommFirebase().getListaCompras(dataSnapshot, constants.getPathMinhaLista(), constants.getFlgMlst());
                    new ProdutoDAO(getApplicationContext()).updateProduto(null, 3);         //deixa todos os produtos disponíveis na despensa / fora da lista de compras

                    for (DBProduto temp : produtosMyLista){
                        new ProdutoDAO(getApplicationContext()).updateProduto(temp, 2);              //atualiza o status, quantidade e unidade dos produtos da lista
                    }
                    //new ProdutoDAO(getApplicationContext()).deleteProduto(constants.getFlgDsp());
                    //new ProdutoDAO(getApplicationContext()).saveList(produtosLista);
                    new ProdutoDAO(getApplicationContext()).updateFlagProduto(constants.getFlgMlst(), firebaseFlag);


                    if (page == PAGER_0)
                        callbacks.updateMyList();
                    else
                        flgUpdateTab0 = true;

                    if (page == PAGER_1)
                        callbacksProductCategoria.updateProducts();

                    //FriendsAdapter mAdapter = new FriendsAdapter(getContext(), userDataset);
                    //mRecycler.setAdapter(mAdapter);

                    /*
                    FragMyList myFragment = (FragMyList) getSupportFragmentManager().findFragmentById(R.id.frmL1);
                    if(myFragment != null && myFragment.isAdded(){
                        myFragment.myRecyclerView.notifyItemRemoved();
                    }*/
                }
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

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        try {
            //Log.println(Log.VERBOSE, "Teste", fragment.toString());
            if (fragment.toString().indexOf("FragMyList") == 0)
                callbacks = (Callbacks) fragment;

            if (fragment.toString().indexOf("FragCategoria") == 0)
                callbacksProductCategoria = (Callbacks) fragment;
        } catch (ClassCastException e) {
            throw new ClassCastException(fragment.toString() + " must implement OnArticleSelectedListener");
        }
    }

//sika fill rápido teto frio
    @Override
    public void onBackPressed() {

        FragmentManager fragmentManager = getSupportFragmentManager();

        for (Fragment temp : fragmentManager.getFragments()){

            if (temp.getTag() != null){
                String frag = temp.getTag().replace("frag", "");

                try{
                    int fragNumber = Integer.parseInt(frag);
                    if ((fragNumber == page) || (page == PAGER_2 && fragNumber % 2 == 0)) {
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

    private void setupTabIcons(int pageMode) {
        //tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        //tabLayout.getTabAt(1).setIcon(tabIcons[2]);
        //tabLayout.getTabAt(2).setIcon(tabIcons[4]);

        ivTab0.setImageResource(tabIcons[0]);
        ivTab1.setImageResource(tabIcons[2]);
        ivTab2.setImageResource(tabIcons[4]);

        //tabLayout.setTabTextColors(R.color.colorMyListCategory, R.color.colorFontProduct);

        //tabLayout.getTabAt(0);

        switch (pageMode) {
            case 0:
                //tabLayout.getTabAt(0).setIcon(tabIcons[1]);
                tabLayout.setTabTextColors(Color.parseColor("#727272"), getBaseContext().getColor(R.color.colorTab0));
                //tabLayout.setTabTextColors(Color.parseColor("#727272"), getResources().getColor(R.color.colorTab0, getTheme()));
                ivTab0.setImageResource(tabIcons[1]);
                break;

            case 1:
                //tabLayout.getTabAt(1).setIcon(tabIcons[3]);
                tabLayout.setTabTextColors(Color.parseColor("#727272"), getBaseContext().getColor(R.color.colorTab1));
                ivTab1.setImageResource(tabIcons[3]);
                break;

            case 2:
                //tabLayout.getTabAt(2).setIcon(tabIcons[5]);
                tabLayout.setTabTextColors(Color.parseColor("#727272"), getBaseContext().getColor(R.color.colorTab2));
                ivTab2.setImageResource(tabIcons[5]);
                break;
        }
    }

    /*
    public void ivClickedCpDB(View item){
        if ((item instanceof ImageView) || (item instanceof TextView)) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frmLClear, new FragCategoria((int) item.getTag())).addToBackStack(null).commit();
        }
    }*/

    //Click Dashboard OpcoesLCP - viewpager - 2
    public void ivClickedOpLCP(View item){
        if ((item instanceof ImageView) || (item instanceof TextView)) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            switch ((int) item.getTag()){
                case 0:
                    //fragmentTransaction.replace(R.id.frmLClearOther, new FragSaveProduct()).commit();
                    fragmentTransaction.replace(R.id.frmLClearOther, new FragSaveProduct(), "frag" + PAGER_2).commit(); //todos do primeiro nível devem ser camados com PAGER_2
                    break;

                case 1:
                    Log.println(Log.VERBOSE, "Teste: ", "Data: " + new Random().nextInt(constants.rangeRandom));
                    break;
            }

            //fragmentTransaction.replace(R.id.frmLClearOther, new FragSaveProduct((int) item.getTag())).addToBackStack(null).commit();
            //fragmentTransaction.replace(R.id.frmLClearOther, new FragSaveProduct((int) item.getTag())).commit();
            //fragmentTransaction.replace(R.id.frmLClearOther, new FragSaveProduct((int) item.getTag())).addToBackStack("DashBoardOpcoesLCP").commit();
        }
    }

    /*
    public void ivClickedCpDB_S(View item){
        if ((item instanceof ImageView) || (item instanceof TextView)) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //fragmentTransaction.replace(R.id.frmLClearOther, new FragSaveProduct((int) item.getTag())).addToBackStack(null).commit();
            //fragmentTransaction.replace(R.id.frmLClearOther, new FragSaveProduct((int) item.getTag())).commit();
            fragmentTransaction.replace(R.id.frmLClearOther, new FragSaveProduct((int) item.getTag())).addToBackStack("DashBoardOpcoesLCP").commit();
        }
    }*/

    public void callFragmentDefault(int fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //todos da viewpager 1 terão número impar e todos da viewpager 2 terão número par.
        switch (fragment){
            case 1:
                fragmentTransaction.replace(R.id.frmLClear, new DashBoardCategoria()).commit();
                //fragmentTransaction.replace(R.id.frmLClear, new FragCategoria((int) item.getTag()), "frag1").addToBackStack(null).commit();
                //fragmentTransaction.replace(R.id.frmLClear, new FragCategoria((int) item.getTag()), "page1").addToBackStack(null).commit();
                break;

            case 2:
                //fragmentTransaction.replace(R.id.frmLClearOther, new FragSaveProduct((int) item.getTag())).commit();
                //fragmentTransaction.replace(R.id.frmLClearOther, new DashBoardCategoria()).commit();
                //fragmentTransaction.replace(R.id.frmLClearOther, new FragSaveProduct((int) item.getTag()), "page2").addToBackStack(null).commit();

                //funciondo
                //fragmentTransaction.replace(R.id.frmLClearOther, new DashBoardCategoria()).commit();

                fragmentTransaction.replace(R.id.frmLClearOther, new DashBoardOpcoesLCP()).commit();
                break;

            case 4:
                //new ConstantsApp().getSaveProduto().setCategoria(-1);
                sProduto.setCategoria(-1);
                fragmentTransaction.replace(R.id.frmLClearOther, new FragSaveProduct(), "frag" + PAGER_2).commit();
                break;
        }
    }

    //click no dashboardCategoria
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

                    //funcionando
                    //fragmentTransaction.replace(R.id.frmLClearOther, new FragSaveProduct((int) item.getTag()), "frag" + page).commit();


                    //new ConstantsApp().setSaveProduto(null);
                    //DBProduto sProduto = new ConstantsApp().getSaveProduto();
                    /*
                    if (sProduto.getCategoria() != -1) {
                        new CommFirebase().sendDataInt(dbOutStatus, new ConstantsApp().getPathDespensa()+"/"+item.getTag()+"/"+sProduto.getNome(), sProduto.getUnidade());

                        sProduto.setCategoria(-1);
                        Snackbar.make(item, "Salvou!", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                        //fragmentTransaction.replace(R.id.frmLClearOther, new DashBoardOpcoesLCP()).commit();
                        fragmentTransaction.replace(R.id.frmLClearOther, new FragSaveProduct(), "frag" + PAGER_2).commit();
                    }
                     */
                    new CommFirebase().sendDataInt(dbOutStatus, constants.getPathDespensa()+"/"+item.getTag()+"/"+sProduto.getNome(), sProduto.getUnidade());
                    new CommFirebase().sendDataInt(dbOutStatus, constants.getPathDespensa()+constants.getPathFlgDsp(), new Random().nextInt(constants.rangeRandom));

                    sProduto.setCategoria(-1);
                    Snackbar.make(item, getString(R.string.saveOk), Snackbar.LENGTH_LONG).setAction("Action", null).show();

                    //fragmentTransaction.replace(R.id.frmLClearOther, new DashBoardOpcoesLCP()).commit();
                    fragmentTransaction.replace(R.id.frmLClearOther, new FragSaveProduct(), "frag" + PAGER_2).commit();
                    break;
            }
        }
    }

    public void clickIvTabs (View view){
        if ((view instanceof ImageView)){
            switch (view.getId()){
                case R.id.ivTab0:
                    tabLayout.selectTab(tabLayout.getTabAt(0));
                    break;

                case R.id.ivTab1:
                    tabLayout.selectTab(tabLayout.getTabAt(1));
                    break;

                case R.id.ivTab2:
                    tabLayout.selectTab(tabLayout.getTabAt(2));
                    break;
            }
        }
    }

    public DBProduto getsProduto() {
        return sProduto;
    }

    public void setsProduto(DBProduto sProduto) {
        this.sProduto = sProduto;
    }

    public int getPage() {
        return page;
    }

    public DatabaseReference getDbOutStatus() {
        return dbOutStatus;
    }

    public boolean isFlgUpdateTab0() {
        return flgUpdateTab0;
    }

    public void setFlgUpdateTab0(boolean flgUpdateTab0) {
        this.flgUpdateTab0 = flgUpdateTab0;
    }
}