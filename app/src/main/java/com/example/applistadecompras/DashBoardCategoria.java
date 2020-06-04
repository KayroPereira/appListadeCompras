package com.example.applistadecompras;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;


//public class DashBoardCategoria extends Fragment implements FrameLayout.OnFocusChangeListener {
//public class DashBoardCategoria extends Fragment implements View.OnLayoutChangeListener {
//public class DashBoardCategoria extends Fragment implements FrameLayout.OnFocusChangeListener{
public class DashBoardCategoria extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int modo;

    /*
    public DashBoardCategoria(int modo) {
        this.modo = modo;
        new ConstantsApp().setModoDashBoard(modo);
    }
    */

    public DashBoardCategoria() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param //param1 Parameter 1.
     * @param //param2 Parameter 2.
     * @return A new instance of fragment Frag1.
     */

    /*
    // TODO: Rename and change types and number of parameters
    public static DashBoardCategoria newInstance(String param1, String param2) {
        DashBoardCategoria fragment = new DashBoardCategoria();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    void handler(View v){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dash_board_categoria, container, false);

        view.setOnClickListener(this::handler);
        //Log.println(Log.VERBOSE, "Teste", "Modo: " + new ConstantsApp().getModoDashBoard() + " Tag: " + view.getTag());

        //view.setOnClickListener(this::ivClickedCpDB);
        //FrameLayout frmL1 = (FrameLayout) view.findViewById(R.id.frmL1);
        //frmL1.setOnFocusChangeListener(this);

        //view.addOnLayoutChangeListener(this);
        //view.setOnFocusChangeListener(this);
/*
        frmL1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Log.println(Log.VERBOSE, "Teste", "Modo: " + new ConstantsApp().getModoDashBoard());
            }
        });
*/
        ConstantsApp constants = new ConstantsApp();
        ConstraintLayout fml = (ConstraintLayout) ((FrameLayout) view).getChildAt(0);
        fml = (ConstraintLayout) fml.getChildAt(0);

        int categoriaItem = 0;
        for (int i = 0; i < fml.getChildCount(); i++) {

            View comp = fml.getChildAt(i);

            if (comp instanceof ConstraintLayout) {
                int cont = 0;
                for (int item = 0; item < ((ConstraintLayout) comp).getChildCount(); item++) {
                    View vTemp = ((ConstraintLayout) comp).getChildAt(item);

                    if (vTemp instanceof TextView) {
                        ((TextView) vTemp).setText(constants.getNameCategoryItem(categoriaItem));
                        vTemp.setTag(categoriaItem);
                        ((TextView) vTemp).setTextColor(getContext().getColor(R.color.colorTab1));
                        cont++;
                    }

                    if (vTemp instanceof ImageView) {
                        ((ImageView) vTemp).setImageResource(this.getContext().getApplicationContext().getResources().getIdentifier("ct"+categoriaItem, "drawable", getActivity().getPackageName()));
                        vTemp.setTag(categoriaItem);
                        cont++;
                    }

                    if (cont == 2)
                        categoriaItem++;
                }
            }
        }
/*
        btTela2DB = view.findViewById(R.id.btTela2DB);

        btTela2DB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frmLClear, new frag_categoria(1)).addToBackStack(null).commit();
            }
        });

 */
        return view;
    }
/*
    @Override
    public void onFocusChange(View view, boolean b) {
        Log.println(Log.VERBOSE, "Teste", "Modo: " + new ConstantsApp().getModoDashBoard());
    }*/

/*
    @Override
    public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
        Log.println(Log.VERBOSE, "Teste", "Modo: " + new ConstantsApp().getModoDashBoard() + " Par: " + mParam1 + " Modo Int: " + modo);
    }*/

/*
    @Override
    public void onFocusChange(View view, boolean b) {
        Log.println(Log.VERBOSE, "Teste", "Modo: " + new ConstantsApp().getModoDashBoard() + " Modo Int: " + modo);
    }
 */
}













/*


public class DashBoardCategoria extends Fragment {

    private Button btTela2DB;
    private FragmentManager fragmentTransaction;
    private frag_categoria testeFragment;
    private FragmentTransaction transaction;

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.dash_board_categoria, container, false);
        //FragmentManager fm = view.getSupportFragmentManager();

        View view = inflater.inflate(R.layout.dash_board_categoria, container, false);
        //FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.replace(R.id.frmLClear, new frag_categoria()).commit();


        btTela2DB = view.findViewById(R.id.btTela2DB);

        btTela2DB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frmL1, new frag_categoria()).addToBackStack(null).commit();
            }
        });

 /*
        btTela2DB = view.findViewById(R.id.btTela2DB);

        //FragmentManager fm = getFragmentManager();

        //fragmentTransaction = getFragmentManager();
        //fragmentTransaction = getActivity().getSupportFragmentManager();


        fragmentTransaction = getChildFragmentManager();
        //fragmentTransaction = getChildFragmentManager();

        //fragmentTransaction = getActivity().getSupportFragmentManager();


        //fragmentTransaction = getActivity().getSupportFragmentManager();

        transaction = fragmentTransaction.beginTransaction();
        /*
        testeFragment = new frag_categoria();
        transaction = fragmentTransaction.beginTransaction();
        */
/*
        TrocaOleoFragment frag_troca_oleo = (TrocaOleoFragment)fm.findFragmentByTag("frag_troca_oleo");
        if(frag_troca_oleo == null) {
            frag_troca_oleo = new TrocaOleoFragment();
        }
        ft.replace(R.id.layout_central, frag_troca_oleo, "frag_troca_oleo");
*/

/*
        FragmentManager manager = getSupportFragmentManager();
        fragment_name fragment_name = new fragment_name();
        manager.beginTransaction().replace(R.id.layoutasubstituir, fragment_name, fragment_name.getTag()).commit();
*/

/*
        btTela2DB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragment = fragmentManager.findFragmentById(R.id.fragment_cart);
                final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.hide(fragment);
                fragmentTransaction.commit();

                 */


                //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //transaction.add(R.id.clTela2_1, testeFragment, "Tela2-2");
                //if(isChecked){
                //transaction.show(testeFragment);
                //transaction.commit();
                //}else{
                  //  fragmentTransaction.hide(shower_fragment);
                   // fragmentTransaction.commit();

                //}

                //fragmentManager?.beginTransaction()?.hide(fragmentManager?.findFragmentByTag(LIST_FRAG_TAG))?.commit();
                //transaction.hide(fragmentTransaction.findFragmentById(R.id.clDashboard)).commit();
                /*
                fragmentTransaction.beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .show(somefrag)
                        .commit();
                 */

                /*
                fragmentTransaction.beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .show(testeFragment)
                        .commit();

                */
                /*
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.clDashboard, new frag_categoria(), "teste");
                ft.commit();

                 */

                /*
                FragmentActivity mainActivity = getActivity();

                if(mainActivity instanceof MainActivity)
                    ((MainActivity) mainActivity).setCurrentItemPager(R.id.clTela2_1);
                    //((MainActivity) mainActivity).setCurrentItemPager(R.layout.frag_categoria);

                */
                //if(v.getId() == btCone.getId()){
                //Toast.makeText(getActivity(),"TESTEBUTTON",Toast.LENGTH_SHORT).show();

                //testeFragment = (frag_categoria)fragmentTransaction.findFragmentById(R.id.clTela2_1);

                /*
                testeFragment = (frag_categoria)fragmentTransaction.findFragmentById(R.id.clTela2_1);

                if(testeFragment == null)
                    testeFragment = new frag_categoria();

                 */

                //testeFragment = new frag_categoria();

                //transaction.hide(DashBoardCategoria());

                //frag_categoria testeFragment = new frag_categoria();

                //transaction.replace(R.id.container_fragment, testeFragment);
                //transaction.replace(R.id.clTela2, testeFragment);

                //transaction.replace(R.id.clDashboard, testeFragment);
                //transaction.replace(R.id.clTela2_1, testeFragment);
                //transaction.replace(R.id.clMain, testeFragment);

                //transaction.hide(DashBoardCategoria());

                //transaction.replace(R.id.clDashboard, testeFragment, testeFragment.getTag());

                /*
                try {
                    this.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                 */

                //transaction.replace(R.id.clDashboard, testeFragment);
                //transaction.replace(R.id.clDashboard, testeFragment);

                /*
                transaction.add(R.id.clDashboard, testeFragment);

                //transaction.addToBackStack(null);

                transaction.commit();
                //transaction.commitNowAllowingStateLoss();
                //transaction.commitNow();
            }
        });
        */


/*
        return view;
    }
}

 */