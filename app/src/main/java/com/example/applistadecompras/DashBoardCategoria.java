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

public class DashBoardCategoria extends Fragment{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DashBoardCategoria() {
    }

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
        return view;
    }
}