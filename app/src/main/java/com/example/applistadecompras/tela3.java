package com.example.applistadecompras;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class tela3 extends Fragment {

    private TextView tv1;

    private int i;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        i = 0;
        View view = inflater.inflate(R.layout.tela3, container, false);

        TextView tv = view.findViewById(R.id.textView_3);
        tv.setText("Você está na terceira aba");

        tv1 = view.findViewById(R.id.textView2_3);

        Button bt = view.findViewById(R.id.button_3);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv1.setText("Cont: " + i++);
            }
        });

        return view;
    }
}