package com.example.applistadecompras;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Produto> produtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        //rv.setLayoutManager(new LinearLayoutManager(this));

        produtos = new Produto().createContactsList(20);

        ProdutoAdapter adapter = new ProdutoAdapter(produtos, this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //rv.setItemViewCacheSize(produtos.size());
    }
}

class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ViewHolder> {

    private List<Produto> mProdutos;
    private Context context;

    public ProdutoAdapter(List<Produto> produtos, Context context) {
        this.mProdutos = produtos;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, EditText.OnFocusChangeListener {

        final TextView tvProduto;
        final EditText etQuantidade;
        final RadioButton rbUn;
        final RadioButton rbMl;
        final RadioButton rbKg;
        final ImageView ivSend;
        final RadioGroup rgUnidade;

        public ViewHolder(View itemView) {
            super(itemView);

            tvProduto = (TextView) itemView.findViewById(R.id.tvProduto);
            etQuantidade = (EditText) itemView.findViewById(R.id.etQuantidade);
            rbUn = (RadioButton) itemView.findViewById(R.id.rbUn);
            rbMl = (RadioButton) itemView.findViewById(R.id.rbMl);
            rbKg = (RadioButton) itemView.findViewById(R.id.rbKg);
            ivSend = (ImageView) itemView.findViewById(R.id.ivSendLTS);
            rgUnidade = (RadioGroup) itemView.findViewById(R.id.rgUnidade);

            ivSend.setOnClickListener(this);
            rgUnidade.setOnCheckedChangeListener(this);
            etQuantidade.setOnFocusChangeListener(this);

        }

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            //int radioButtonID = rgUnidade.getCheckedRadioButtonId();
            View radioButton = rgUnidade.findViewById(rgUnidade.getCheckedRadioButtonId());
            int radioId = rgUnidade.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) rgUnidade.getChildAt(radioId);
            //final int currentPosition = getAdapterPosition();

            if (btn.isChecked()){
                mProdutos.get(getAdapterPosition()).setUnidade(radioId);
            }
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                //User user = users.get(position);
                // We can access the data within the views
                Produto pd = new Produto();
                pd.setmName(mProdutos.get(position).getmName());
                pd.setQuantidade(mProdutos.get(position).getQuantidade());
                pd.setUnidade(mProdutos.get(position).getUnidade());
                Toast.makeText(view.getContext(), pd.getmName() + " " + pd.getQuantidade() + (pd.getUnidade() == 0 ? "Un" : pd.getUnidade() == 1 ? "ml" : "Kg"), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFocusChange(View view, boolean b) {
            if (!b)
                mProdutos.get(getAdapterPosition()).setQuantidade(Float.parseFloat(etQuantidade.getText().toString()));
        }
    }

    @Override
    public ProdutoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View produtotView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        ViewHolder viewHolder = new ViewHolder(produtotView);

        return viewHolder;
    }

    private void printLog(){
        for (Produto temp : mProdutos)
            Log.println(Log.VERBOSE, "Inform", "Produto: " + temp.getmName() + " - Qnt: " + temp.getQuantidade() + " - Un: " + temp.getUnidade());
    }

    @Override
    public void onBindViewHolder(ProdutoAdapter.ViewHolder viewHolder, int position) {

        ViewHolder hold = (ViewHolder) viewHolder;
        Produto produtos = mProdutos.get(position);

        viewHolder.setIsRecyclable(false);

        //getView(position, hold.itemView, getAdapterPosition());

        hold.tvProduto.setText(produtos.getmName());
        hold.etQuantidade.setText(produtos.getQuantidade()+"");

        switch (produtos.getUnidade()){
            case 0:
                hold.rbUn.setChecked(true);
                break;

            case 1:
                hold.rbMl.setChecked(true);
                break;

            case 2:
                hold.rbKg.setChecked(true);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mProdutos.size();
    }
}

class Produto {
    private String mName;
    private float quantidade;
    private int unidade;

    public Produto(){

    }

    public Produto(float quantidade, int unidade) {
        this.quantidade = quantidade;
        this.unidade = unidade;
    }

    public Produto(String mName, float quantidade, int unidade) {
        this.mName = mName;
        this.quantidade = quantidade;
        this.unidade = unidade;
    }

    public String getmName() {
        return mName;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public int getUnidade() {
        return unidade;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public void setUnidade(int unidade) {
        this.unidade = unidade;
    }

    private int lastContactId = 0;
    public ArrayList<Produto> createContactsList(int numContacts) {
        ArrayList<Produto> produtos = new ArrayList<Produto>();

        for (int i = 1; i <= numContacts; i++) {
            //produtos.add(new Produto("Produto " + ++lastContactId, 1,i % 3));
            //produtos.add(new Produto("Produto " + ++lastContactId, i % 3,i % 3));
            produtos.add(new Produto("Produto " + ++lastContactId, i-1,i % 3));
        }
        return produtos;
    }
}