package br.com.goldencode.softsports;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

public class NovoEventoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View viewEvento = inflater.inflate(R.layout.fragment_novo_evento, container, false);

        final ScrollView scrollView = (ScrollView) viewEvento.findViewById(R.id.fragment_evento);
        AppCompatEditText editTextTituloEvento = (AppCompatEditText) viewEvento.findViewById(R.id.edtTituloEvento);
        AppCompatEditText editTextDataCriacao = (AppCompatEditText) viewEvento.findViewById(R.id.edtTituloEvento);
        AppCompatButton buttonCriarNovoEvento = (AppCompatButton) viewEvento.findViewById(R.id.btnCriarEvento);

        buttonCriarNovoEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar snackbar = Snackbar.make(scrollView, "Evento criado com sucesso.", Snackbar.LENGTH_LONG);
                View sbView = snackbar.getView();
                sbView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorSuccess));
                sbView.getLayoutParams().height = 150;
                sbView.setPadding(18, 0, 0, 18);
                snackbar.show();

            }
        });

        Evento evento = new Evento();

        return viewEvento;
    }



}
