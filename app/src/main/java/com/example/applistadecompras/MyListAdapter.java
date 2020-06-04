package com.example.applistadecompras;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.applistadecompras.Communication.CommFirebase;
import com.google.firebase.database.DatabaseReference;

import java.util.List;
import java.util.Random;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {

//private List<Produto> mProdutos;
private List<DBProduto> mProdutos;
private Context context;

private ConstantsApp constants = new ConstantsApp();

//public ProdutoAdapter(List<Produto> produtos, Context context) {
public MyListAdapter(List<DBProduto> produtos, Context context) {
        this.mProdutos = produtos;
        this.context = context;
    }

    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View produtotView = LayoutInflater.from(context).inflate(R.layout.item_my_list, parent, false);

        ViewHolder viewHolder = new ViewHolder(produtotView);
        return viewHolder;
    }

    @SuppressLint({"ResourceAsColor", "Range"})
    @Override
    public void onBindViewHolder(MyListAdapter.ViewHolder viewHolder, int position) {

        ViewHolder hold = (ViewHolder) viewHolder;
        //Produto produtos = mProdutos.get(position);
        DBProduto produtos = mProdutos.get(position);

        viewHolder.setIsRecyclable(false);
        //hold.tvProduto.setText("          "+produtos.getNome()+ " - " + produtos.getQuantidade()  + " " + constants.getNameUnidade()[produtos.getUnidade()]);

        switch(produtos.getId()){
            case -1:
                hold.tvProduto.setText(produtos.getNome());
                //hold.tvProduto.setTextColor(context.getColor(R.color.colorMyListCategory));
                //hold.tvProduto.setTextColor(context.getColor(R.color.colorFontMain));
                hold.tvProduto.setTextColor(context.getColor(R.color.colorFontItemCategoria));
                hold.tvProduto.setTextSize(16);
                hold.ivCategoriaMyList.setImageResource(context.getApplicationContext().getResources().getIdentifier(produtos.getCategoria() != -1 ? "ct"+produtos.getCategoria() : "basket", "drawable", context.getPackageName()));
                hold.itemView.setBackgroundResource(R.drawable.gradient);
                //hold.itemView.setAlpha(5);
                break;

            case -2:
                hold.tvProduto.setText(produtos.getNome());
                hold.tvProduto.setTextColor(context.getColor(R.color.colorPurchasedProducts));
                //hold.tvProduto.setTextSize(16);
                if (produtos.getCategoria() != -2)
                    hold.tvProduto.setPaintFlags(hold.tvProduto.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                hold.ivCategoriaMyList.setVisibility(View.GONE);
                //hold.ivCategoriaMyList.setImageResource(context.getApplicationContext().getResources().getIdentifier("basket", "drawable", context.getPackageName()));
                break;

            default:
                //hold.tvProduto.setTextColor(context.getColor(R.color.colorFontProduct));
                hold.tvProduto.setTextColor(context.getColor(R.color.colorFontItem));
                hold.tvProduto.setTextSize(17);
                hold.tvProduto.setText(produtos.getNome()+ " - " + produtos.getQuantidade()  + " " + constants.getNameUnidade()[produtos.getUnidade()]);
                hold.ivCategoriaMyList.setVisibility(View.GONE);

                /*
                if (produtos.getStatus() == constants.getStatusOff())
                    hold.tvProduto.setPaintFlags(hold.tvProduto.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);*/
        }

        /*
        if (produtos.getId() == -1){
            hold.tvProduto.setText(produtos.getNome());
            //hold.tvProduto.setTextColor(R.color.colorMyListCategory);
            //hold.tvProduto.setTextColor(Color.rgb(0x0b0, 0x00, 0x20));
            hold.tvProduto.setTextColor(context.getColor(R.color.colorMyListCategory));
            hold.tvProduto.setTextSize(20);
            hold.ivCategoriaMyList.setImageResource(context.getApplicationContext().getResources().getIdentifier("none_day", "drawable", context.getPackageName()));
        }else{
            hold.tvProduto.setText(produtos.getNome()+ " - " + produtos.getQuantidade()  + " " + constants.getNameUnidade()[produtos.getUnidade()]);
            hold.ivCategoriaMyList.setVisibility(View.GONE);

            if (produtos.getStatus() == constants.getStatusOff())
                hold.tvProduto.setPaintFlags(hold.tvProduto.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }*/

        //getView(position, hold.itemView, getAdapterPosition());

        /*
        hold.tvProduto.setText(produtos.getNome());
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
        }*/
    }

    //public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, EditText.OnFocusChangeListener {
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        final TextView tvProduto;
        final ImageView ivCategoriaMyList;
        /*
        final EditText etQuantidade;
        final RadioButton rbUn;
        final RadioButton rbMl;
        final RadioButton rbKg;
        final ImageView ivSend;
        final RadioGroup rgUnidade;*/

        public ViewHolder(View itemView) {
            super(itemView);

            tvProduto = (TextView) itemView.findViewById(R.id.tvProdutoMyList);
            ivCategoriaMyList = (ImageView) itemView.findViewById(R.id.ivCategoriaMyList);
            tvProduto.setOnClickListener(this);
            
            /*
            etQuantidade = (EditText) itemView.findViewById(R.id.etQuantidade);
            rbUn = (RadioButton) itemView.findViewById(R.id.rbUn);
            rbMl = (RadioButton) itemView.findViewById(R.id.rbMl);
            rbKg = (RadioButton) itemView.findViewById(R.id.rbKg);
            ivSend = (ImageView) itemView.findViewById(R.id.ivSendLTS);
            rgUnidade = (RadioGroup) itemView.findViewById(R.id.rgUnidade);

            ivSend.setOnClickListener(this);
            rgUnidade.setOnCheckedChangeListener(this);
            etQuantidade.setOnFocusChangeListener(this);*/
        }

        /*
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            //int radioButtonID = rgUnidade.getCheckedRadioButtonId();

            View radioButton = rgUnidade.findViewById(rgUnidade.getCheckedRadioButtonId());
            int radioId = rgUnidade.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) rgUnidade.getChildAt(radioId);
            
            //final int currentPosition = getAdapterPosition();

            if (btn.isChecked()){
                //mProdutos.get(getAdapterPosition()).setUnidade(radioId);
                mProdutos.get(getAdapterPosition()).setUnidade(radioId);
            }
        }*/
        

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                //User user = users.get(position);
                // We can access the data within the views
                /*
                Produto pd = new Produto();
                pd.setmName(mProdutos.get(position).getmName());
                pd.setQuantidade(mProdutos.get(position).getQuantidade());
                pd.setUnidade(mProdutos.get(position).getUnidade());
                Toast.makeText(view.getContext(), pd.getmName() + " " + pd.getQuantidade() + (pd.getUnidade() == 0 ? "Un" : pd.getUnidade() == 1 ? "ml" : "Kg"), Toast.LENGTH_SHORT).show();
                 */

                //String value = Float.parseFloat(etQuantidade.getText().toString())+"#"+mProdutos.get(position).getUnidade()+"#"+mProdutos.get(position).getStatus();
                DatabaseReference dbOutStatus = ((MainActivity) context).getDbOutStatus();
                //String value = "";
                //String path = "";

                String path = constants.getPathMinhaLista();
                String value = mProdutos.get(position).getQuantidade()+"#"+mProdutos.get(position).getUnidade()+"#";

                if (mProdutos.get(position).getStatus() == constants.getStatusWait()) {
                    new CommFirebase().sendDataString(dbOutStatus,path + "/" + mProdutos.get(position).getCategoria() + "/" + mProdutos.get(position).getNome(), value+constants.getStatusOff());
                }else if (mProdutos.get(position).getStatus() == constants.getStatusOff()){
                    new CommFirebase().sendDataString(dbOutStatus,path + "/" + mProdutos.get(position).getCategoria() + "/" + mProdutos.get(position).getNome(), value+constants.getStatusWait());
                }
                new CommFirebase().sendDataInt(dbOutStatus, path+constants.getPathFlgMlst(), new Random().nextInt(constants.rangeRandom));

                /*
                FragMyList myFragment = (FragMyList) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                if(myFragment != null && myFragment.isAdded(){
                    myFragment.myRecyclerView.notifyItemRemoved();
                }*/
                
                /*
                if (((MainActivity) context).getPage() == 1){
                    path = constants.getPathMinhaLista();
                    if (mProdutos.get(position).getStatus() == constants.getStatusOn()) {
                        value = Float.parseFloat(etQuantidade.getText().toString())+"#"+mProdutos.get(position).getUnidade()+"#"+ constants.getStatusWait();

                        new CommFirebase().sendDataString(dbOutStatus,path + "/" + mProdutos.get(position).getCategoria() + "/" + mProdutos.get(position).getNome(), value);
                    }else{
                        new CommFirebase().deleteItem(dbOutStatus,path + "/" + mProdutos.get(position).getCategoria() + "/" + mProdutos.get(position).getNome());
                    }
                    new CommFirebase().sendDataInt(dbOutStatus, path+constants.getPathFlgMlst(), new Random().nextInt(constants.rangeRandom));
                }
                DBProduto pd = new DBProduto();
                pd.setNome(mProdutos.get(position).getNome());
                pd.setQuantidade(mProdutos.get(position).getQuantidade());
                pd.setUnidade(mProdutos.get(position).getUnidade());
                Toast.makeText(view.getContext(), pd.getNome() + " " + pd.getQuantidade() + (pd.getUnidade() == 0 ? "Un" : pd.getUnidade() == 1 ? "ml" : "Kg"), Toast.LENGTH_SHORT).show();
                
                 */
            }
        }

        /*
        @Override
        public void onFocusChange(View view, boolean b) {
            if (!b)
                mProdutos.get(getAdapterPosition()).setQuantidade(Float.parseFloat(etQuantidade.getText().toString()));
        }*/
    }

    private void printLog(){
        //for (Produto temp : mProdutos)
        for (DBProduto temp : mProdutos)
            Log.println(Log.VERBOSE, "Inform", "Produto: " + temp.getNome() + " - Qnt: " + temp.getQuantidade() + " - Un: " + temp.getUnidade());
    }

    @Override
    public int getItemCount() {
        return mProdutos.size();
    }
}
