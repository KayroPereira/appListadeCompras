package com.example.applistadecompras;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applistadecompras.Communication.CommFirebase;
import com.google.firebase.database.DatabaseReference;

import java.util.List;
import java.util.Random;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ViewHolder>{

    private ContactsAdapterListener listener;

    //private List<Produto> mProdutos;
    private List<DBProduto> mProdutos;
    private Context context;

    /*
    private TextView tvProduto;
    private EditText etQuantidade;
    private RadioButton rbUn;
    private RadioButton rbMl;
    private RadioButton rbKg;
    private ImageView ivSend;
    private RadioGroup rgUnidade;
    private ConstraintLayout clItem_2;
     */

    private ConstantsApp constants = new ConstantsApp();


    public interface ContactsAdapterListener {
        void onContactSelected(DBProduto tag);
    }

    //public ProdutoAdapter(List<Produto> produtos, Context context) {
    /*
    //recebe a interface como parametro
    public ProdutoAdapter(List<DBProduto> produtos, Context context, ContactsAdapterListener listener) {
    */
    public ProdutoAdapter(List<DBProduto> produtos, Context context) {
        this.mProdutos = produtos;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ProdutoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View produtotView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        ViewHolder viewHolder = new ViewHolder(produtotView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProdutoAdapter.ViewHolder viewHolder, int position) {

        ViewHolder hold = (ViewHolder) viewHolder;
        //Produto produtos = mProdutos.get(position);
        DBProduto produtos = mProdutos.get(position);

        viewHolder.setIsRecyclable(false);

        /*
        InputMethodManager imm =  (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etQuantidade.getWindowToken(), 0);
         */

        hideSoftKeyboard(hold.etQuantidade);

        //getView(position, hold.itemView, getAdapterPosition());

        hold.tvProduto.setText(produtos.getNome());
        hold.tvProduto.setTextSize(16);
        hold.etQuantidade.setText(produtos.getQuantidade()+"");
        hold.ivSend.setImageResource(context.getApplicationContext().getResources().getIdentifier(produtos.getStatus() == constants.getStatusOn() ? "basket" : "clbasket", "drawable", context.getPackageName()));

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

        boolean status = produtos.getStatus() == constants.getStatusOn() ? true : false;
        hold.etQuantidade.setEnabled(status);
        hold.rgUnidade.setEnabled(status);
        hold.rbUn.setEnabled(status);
        hold.rbMl.setEnabled(status);
        hold.rbKg.setEnabled(status);

/*
        hold.tvProduto.setText(produtos.getNome());
        hold.tvProduto.setTextSize(16);
        hold.etQuantidade.setText(produtos.getQuantidade()+"");
        hold.ivSend.setImageResource(context.getApplicationContext().getResources().getIdentifier(produtos.getStatus() == constants.getStatusOn() ? "basket" : "clbasket", "drawable", context.getPackageName()));

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

 */
    }

    //public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, EditText.OnFocusChangeListener {
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, View.OnFocusChangeListener {

        final TextView tvProduto;
        final EditText etQuantidade;
        final RadioButton rbUn;
        final RadioButton rbMl;
        final RadioButton rbKg;
        final ImageView ivSend;
        final RadioGroup rgUnidade;
        final ConstraintLayout clItem_2;

        public ViewHolder(View itemView) {
            super(itemView);

            tvProduto = (TextView) itemView.findViewById(R.id.tvProdutoItem);
            etQuantidade = (EditText) itemView.findViewById(R.id.etQuantidade);
            rbUn = (RadioButton) itemView.findViewById(R.id.rbUn);
            rbMl = (RadioButton) itemView.findViewById(R.id.rbMl);
            rbKg = (RadioButton) itemView.findViewById(R.id.rbKg);
            ivSend = (ImageView) itemView.findViewById(R.id.ivSendLTS);
            rgUnidade = (RadioGroup) itemView.findViewById(R.id.rgUnidade);
            clItem_2 = (ConstraintLayout) itemView.findViewById(R.id.clItem_2);

            tvProduto.setOnClickListener(this);
            clItem_2.setOnClickListener(this);

            ivSend.setOnClickListener(this);
            //rgUnidade.setOnClickListener(this);
            rgUnidade.setOnCheckedChangeListener(this);
            etQuantidade.setOnFocusChangeListener(this);


            ivSend.setOnFocusChangeListener(this);

            etQuantidade.setSelectAllOnFocus(true);
            //clItem_2.setOnFocusChangeListener(this);
            //rgUnidade.setOnFocusChangeListener(this);
            //rbUn.setOnFocusChangeListener(this);
            //rbMl.setOnFocusChangeListener(this);
        }

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            //int radioButtonID = rgUnidade.getCheckedRadioButtonId();

            /*
            View radioButton = rgUnidade.findViewById(rgUnidade.getCheckedRadioButtonId());
            int radioId = rgUnidade.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) rgUnidade.getChildAt(radioId);
            */


            //final int currentPosition = getAdapterPosition();
/*
            InputMethodManager imm =  (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            //imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(radioGroup.getWindowToken(), 0);

            radioGroup.getWindowToken().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

 */
            //InputMethodManager imm =  (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

            /*
            etQuantidade.clearFocus();
            InputMethodManager imm =  (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(etQuantidade.getWindowToken(), 0);
             */



            /*
            hideSoftKeyboard(etQuantidade);

            if (btn.isChecked()){
                //mProdutos.get(getAdapterPosition()).setUnidade(radioId);
                mProdutos.get(getAdapterPosition()).setUnidade(radioId);
            }
            */

            View radioButton = rgUnidade.findViewById(rgUnidade.getCheckedRadioButtonId());

            //View radioButton = rgUnidade.findViewById(i);
            int radioId = rgUnidade.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) rgUnidade.getChildAt(radioId);

            hideSoftKeyboard(etQuantidade);

            if (btn.isChecked()){
                //mProdutos.get(getAdapterPosition()).setUnidade(radioId);
                mProdutos.get(getAdapterPosition()).setUnidade(radioId);
            }
        }

        @Override
        public void onClick(View view) {

            /*
            etQuantidade.clearFocus();
            InputMethodManager imm =  (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
             */
            hideSoftKeyboard(etQuantidade);

            int position = getAdapterPosition(); // gets item position
            //if ((position != RecyclerView.NO_POSITION) && view.getTag().toString().equals("ivSendLTS")) { // Check if an item was deleted, but the user clicked it before the UI removed it
            if ((position != RecyclerView.NO_POSITION) && view.getTag() != null) { // Check if an item was deleted, but the user clicked it before the UI removed it
                switch (view.getTag().toString()){
                    case "ivSendLTS":
                        DatabaseReference dbOutStatus = ((MainActivity) context).getDbOutStatus();
                        String value = "";
                        String path = "";
                        if (((MainActivity) context).getPage() == 1){
                            path = constants.getPathMinhaLista();
                            if (mProdutos.get(position).getStatus() == constants.getStatusOn()) {
                                value = Float.parseFloat(etQuantidade.getText().toString())+"#"+mProdutos.get(position).getUnidade()+"#"+ constants.getStatusWait();

                                new CommFirebase().sendDataString(dbOutStatus,path + "/" + mProdutos.get(position).getCategoria() + "/" + mProdutos.get(position).getNome(), value);
                                Toast.makeText(view.getContext(), mProdutos.get(position).getNome() + " " + context.getString(R.string.msgProductAdd), Toast.LENGTH_SHORT).show();
                            }else{
                                new CommFirebase().deleteItem(dbOutStatus,path + "/" + mProdutos.get(position).getCategoria() + "/" + mProdutos.get(position).getNome());
                                Toast.makeText(view.getContext(), mProdutos.get(position).getNome() + " " + context.getString(R.string.msgProductRemove), Toast.LENGTH_SHORT).show();
                            }
                            new CommFirebase().sendDataInt(dbOutStatus, path+constants.getPathFlgMlst(), new Random().nextInt(constants.rangeRandom));
                        }
                        break;
                }
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

                //ivSend.setImageResource(context.getApplicationContext().getResources().getIdentifier(mProdutos.get(position).getStatus() == constants.getStatusOn() ? "basket" : "clbasket", "drawable", context.getPackageName()));

                /*
                DBProduto pd = new DBProduto();
                pd.setNome(mProdutos.get(position).getNome());
                pd.setQuantidade(mProdutos.get(position).getQuantidade());
                pd.setUnidade(mProdutos.get(position).getUnidade());
                 */

                //Toast.makeText(view.getContext(), pd.getNome() + " " + pd.getQuantidade() + (pd.getUnidade() == 0 ? "Un" : pd.getUnidade() == 1 ? "ml" : "Kg"), Toast.LENGTH_SHORT).show();

                /*
                //envia informações para o fragment
                listener.onContactSelected(pd);
                 */
                hideSoftKeyboard(etQuantidade);
            }
        }

        @Override
        public void onFocusChange(View view, boolean b) {
            if (!b) {
                mProdutos.get(getAdapterPosition()).setQuantidade(Float.parseFloat(etQuantidade.getText().toString()));
                //InputMethodManager imm =  (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                //imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                hideSoftKeyboard(etQuantidade);
            }else{
                etQuantidade.selectAll();
            }
        }
    }
/*
    public void hideSoftKeyboard() {
        if(view.getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

 */

    public void hideSoftKeyboard(View view) {
        view.clearFocus();
        InputMethodManager imm =  (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
