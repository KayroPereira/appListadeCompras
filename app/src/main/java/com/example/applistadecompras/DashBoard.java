package com.example.applistadecompras;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class DashBoard extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DashBoard() {
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
    public static DashBoard newInstance(String param1, String param2) {
        DashBoard fragment = new DashBoard();
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

    private Button btTela2DB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dash_board, container, false);

        btTela2DB = view.findViewById(R.id.btTela2DB);

        btTela2DB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frmLClear, new tela2()).addToBackStack(null).commit();
            }
        });

        //return inflater.inflate(R.layout.fragment_frag1, container, false);
        return view;
    }
}












/*


public class DashBoard extends Fragment {

    private Button btTela2DB;
    private FragmentManager fragmentTransaction;
    private tela2 testeFragment;
    private FragmentTransaction transaction;

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.dash_board, container, false);
        //FragmentManager fm = view.getSupportFragmentManager();

        View view = inflater.inflate(R.layout.dash_board, container, false);
        //FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.replace(R.id.frmLClear, new tela2()).commit();


        btTela2DB = view.findViewById(R.id.btTela2DB);

        btTela2DB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frmL1, new tela2()).addToBackStack(null).commit();
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
        testeFragment = new tela2();
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
                ft.replace(R.id.clDashboard, new tela2(), "teste");
                ft.commit();

                 */

                /*
                FragmentActivity mainActivity = getActivity();

                if(mainActivity instanceof MainActivity)
                    ((MainActivity) mainActivity).setCurrentItemPager(R.id.clTela2_1);
                    //((MainActivity) mainActivity).setCurrentItemPager(R.layout.tela2);

                */
                //if(v.getId() == btCone.getId()){
                //Toast.makeText(getActivity(),"TESTEBUTTON",Toast.LENGTH_SHORT).show();

                //testeFragment = (tela2)fragmentTransaction.findFragmentById(R.id.clTela2_1);

                /*
                testeFragment = (tela2)fragmentTransaction.findFragmentById(R.id.clTela2_1);

                if(testeFragment == null)
                    testeFragment = new tela2();

                 */

                //testeFragment = new tela2();

                //transaction.hide(DashBoard());

                //tela2 testeFragment = new tela2();

                //transaction.replace(R.id.container_fragment, testeFragment);
                //transaction.replace(R.id.clTela2, testeFragment);

                //transaction.replace(R.id.clDashboard, testeFragment);
                //transaction.replace(R.id.clTela2_1, testeFragment);
                //transaction.replace(R.id.clMain, testeFragment);

                //transaction.hide(DashBoard());

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