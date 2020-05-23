package com.example.applistadecompras;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

//public class SqliteActivity extends AppCompatActivity {
public class SqliteActivity extends Fragment {

    private ImageView ivSendSQL;
    private View viewMain;

    @Override
    //protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sqlite);
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewMain = inflater.inflate(R.layout.activity_sqlite, container, false);

        ivSendSQL = (ImageView) viewMain.findViewById(R.id.ivSendSQL);

        ivSendSQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //carregando os campos
                EditText etCategoria = (EditText) viewMain.findViewById(R.id.etCategoria);
                EditText etProduto = (EditText) viewMain.findViewById(R.id.etProduto);
                EditText etQuantidade = (EditText) viewMain.findViewById(R.id.etQuantidade);
                RadioGroup rgUnidade = (RadioGroup) viewMain.findViewById(R.id.rgUnidade);
                CheckBox cbStatus = (CheckBox) viewMain.findViewById(R.id.cbStatus);

                View radioButton = rgUnidade.findViewById(rgUnidade.getCheckedRadioButtonId());
                //int radioId = rgUnidade.indexOfChild(radioButton);
                RadioButton rbCheck = (RadioButton) rgUnidade.getChildAt(0);

                //pegando os valores
                int categoria = Integer.parseInt(etCategoria.getText().toString());
                String produto = etProduto.getText().toString().toLowerCase();
                float quantidade = Float.parseFloat(etQuantidade.getText().toString());
                boolean status = cbStatus.isChecked();
                int unidade = rbCheck.isChecked() ? 0 : ((RadioButton) rgUnidade.getChildAt(1)).isChecked() ? 1 : 2;

                produto = produto.substring(0,1).toUpperCase().concat(produto.substring(1));

                //salvando os dados
                //ProdutoDAO dao = new ProdutoDAO(getBaseContext());
                ProdutoDAO dao = new ProdutoDAO(inflater.getContext());
                boolean sucesso = dao.salvar(categoria, produto, quantidade, unidade, status);
                if (sucesso) {
                    //limpa os campos
                    etCategoria.setText("");
                    etProduto.setText("");
                    etQuantidade.setText("");
                    cbStatus.setChecked(false);

                    rbCheck = (RadioButton) rgUnidade.getChildAt(0);
                    rbCheck.setChecked(true);

                    Snackbar.make(view, "Salvou!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else {
                    Snackbar.make(view, "Erro ao salvar, consulte os logs!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });
        return viewMain;
    }
}