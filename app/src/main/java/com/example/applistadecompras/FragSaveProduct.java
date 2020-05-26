package com.example.applistadecompras;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.applistadecompras.Communication.CommFirebase;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragSaveProduct extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int categoria;

    public FragSaveProduct(int categoria) {
        this.categoria = categoria;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param //param1 Parameter 1.
     * @param //param2 Parameter 2.
     * @return A new instance of fragment FragSaveProduct.
     */
    /*
    // TODO: Rename and change types and number of parameters
    public static FragSaveProduct newInstance(String param1, String param2) {
        FragSaveProduct fragment = new FragSaveProduct();
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

    private ImageView ivSendSQL;
    private View viewMain;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();

    final DatabaseReference dbOutStatus = reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_frag_save_product, container, false);

        viewMain = inflater.inflate(R.layout.fragment_frag_save_product, container, false);

        ivSendSQL = (ImageView) viewMain.findViewById(R.id.ivSendSQL);

        TextView tvCategoria = (TextView) viewMain.findViewById(R.id.tvCategoria);
        tvCategoria.setText(new ConstantsApp().getNameCategoryItem(categoria));

        ivSendSQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //carregando os campos
                //EditText etCategoria = (EditText) viewMain.findViewById(R.id.etCategoria);
                EditText etProduto = (EditText) viewMain.findViewById(R.id.etProduto);
                //EditText etQuantidade = (EditText) viewMain.findViewById(R.id.etQuantidade);
                RadioGroup rgUnidade = (RadioGroup) viewMain.findViewById(R.id.rgUnidade);
                //CheckBox cbStatus = (CheckBox) viewMain.findViewById(R.id.cbStatus);


                View radioButton = rgUnidade.findViewById(rgUnidade.getCheckedRadioButtonId());
                //int radioId = rgUnidade.indexOfChild(radioButton);
                RadioButton rbCheck = (RadioButton) rgUnidade.getChildAt(0);

                //pegando os valores
                //int categoria = Integer.parseInt(etCategoria.getText().toString());
                String produto = etProduto.getText().toString().toLowerCase();
                //float quantidade = Float.parseFloat(etQuantidade.getText().toString());
                //boolean status = cbStatus.isChecked();
                int unidade = rbCheck.isChecked() ? 0 : ((RadioButton) rgUnidade.getChildAt(1)).isChecked() ? 1 : 2;

                produto = produto.substring(0,1).toUpperCase().concat(produto.substring(1));

                //salvando os dados
                //ProdutoDAO dao = new ProdutoDAO(getBaseContext());
                //ProdutoDAO dao = new ProdutoDAO(inflater.getContext());
                //boolean sucesso = dao.saveItem(categoria, produto, quantidade, unidade, status);
                //boolean sucesso = new CommFirebase().sendDataInt(dbOutStatus, new ConstantsApp().getPathDespensa()+"/"+categoria+"/"+produto, unidade);
                new CommFirebase().sendDataInt(dbOutStatus, new ConstantsApp().getPathDespensa()+"/"+categoria+"/"+produto, unidade);
                //if (sucesso) {
                //limpa os campos
                //etCategoria.setText("");
                etProduto.setText("");
                //etQuantidade.setText("");
                //cbStatus.setChecked(false);

                rbCheck = (RadioButton) rgUnidade.getChildAt(0);
                rbCheck.setChecked(true);

                Snackbar.make(view, "Salvou!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                //} else {
                //Snackbar.make(view, "Erro ao salvar, consulte os logs!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                //}
            }
        });


        return viewMain;
    }
}
