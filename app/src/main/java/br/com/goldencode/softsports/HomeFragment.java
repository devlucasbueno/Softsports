package br.com.goldencode.softsports;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class HomeFragment extends Fragment {

    View view;
    private LinearLayout linearLayout;
    private AppCompatImageButton buttonEsconder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        linearLayout = view.findViewById(R.id.layoutEsconder);
        buttonEsconder = view.findViewById(R.id.btnSetaPraBaixo);

        buttonEsconder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (linearLayout.getVisibility() == View.GONE){
                   linearLayout.setVisibility(View.VISIBLE);
               } else {
                   linearLayout.setVisibility(View.GONE);
               }
            }
        });

        return view;

    }
}
