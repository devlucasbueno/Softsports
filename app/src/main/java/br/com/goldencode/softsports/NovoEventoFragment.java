package br.com.goldencode.softsports;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import static java.lang.System.in;

public class NovoEventoFragment extends Fragment {

    public void snackBarFailed(View viewEvento, String mensagem){

        ScrollView scrollView = (ScrollView) viewEvento.findViewById(R.id.fragment_evento);

        Snackbar snackbar = Snackbar.make(scrollView, mensagem, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorError));
        sbView.getLayoutParams().height = 150;
        sbView.setPadding(18, 0, 0, 18);
        snackbar.show();

    }

    public void snackBarSuccess(View viewEvento, String mensagem){

        ScrollView scrollView = (ScrollView) viewEvento.findViewById(R.id.fragment_evento);

        Snackbar snackbar = Snackbar.make(scrollView, mensagem, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorSuccess));
        sbView.getLayoutParams().height = 150;
        sbView.setPadding(18, 0, 0, 18);
        snackbar.show();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View viewEvento = inflater.inflate(R.layout.fragment_novo_evento, container, false);

        //SQL
        final SQLiteDatabase mDatabase;

        //EditTexts
        final AppCompatEditText editTextTituloEvento = (AppCompatEditText) viewEvento.findViewById(R.id.edtTituloEvento);
        final AppCompatEditText editTextDataEvento = (AppCompatEditText) viewEvento.findViewById(R.id.edtDataEvento);
        final AppCompatEditText editTextDescricao = (AppCompatEditText) viewEvento.findViewById(R.id.edtDesc);
        final AppCompatEditText editTextLocal = (AppCompatEditText) viewEvento.findViewById(R.id.edtLocal);
        final AppCompatEditText editTextHrInicio = (AppCompatEditText) viewEvento.findViewById(R.id.edtHrInicio);
        final AppCompatEditText editTextHrTermino = (AppCompatEditText) viewEvento.findViewById(R.id.edtHrFim);

        final ScrollView scrollView = viewEvento.findViewById(R.id.layoutCardView);

        AppCompatButton buttonCriarNovoEvento = (AppCompatButton) viewEvento.findViewById(R.id.btnCriarEvento);

        Softsports dbHelper = new Softsports(getActivity());
        mDatabase = dbHelper.getWritableDatabase();

        buttonCriarNovoEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog;
                progressDialog = new ProgressDialog(getActivity(), R.style.customAlertDialogStyle);



                if (editTextTituloEvento.getText().toString().trim() == null){
                    snackBarFailed(viewEvento, "O campo título está vazio");
                    return;
                }

                if (editTextLocal.getText().toString().trim() == null){
                    snackBarFailed(viewEvento, "O campo data evento está vazio");
                    return;
                }

                if (editTextDataEvento.getText().toString().trim() == null){
                    snackBarFailed(viewEvento, "O campo descrição está vazio");
                    return;
                }

                if (editTextHrInicio.getText().toString().trim() == null){
                    snackBarFailed(viewEvento, "O campo local está vazio");
                    return;
                }

                if (editTextHrTermino.getText().toString().trim() == null){
                    snackBarFailed(viewEvento, "O campo horário de início está vazio");
                    return;
                }

                if (editTextDescricao.getText().toString().trim() == null){
                    snackBarFailed(viewEvento, "O campo hora término está vazio");
                    return;
                }

                final String titulo = editTextTituloEvento.getText().toString().trim();
                final String local = editTextLocal.getText().toString().trim();
                final String dataEvento = editTextDataEvento.getText().toString().trim();
                final String hrInicio = editTextHrInicio.getText().toString().trim();
                final String hrTermino = editTextHrTermino.getText().toString().trim();
                final String descricao = editTextDescricao.getText().toString().trim();

                int cod_esporte = 0;

                RadioGroup selectedId = (RadioGroup) viewEvento.findViewById(R.id.radioGroupCadastro);
                switch (selectedId.getCheckedRadioButtonId())
                {
                    case R.id.radioFutebol:
                        cod_esporte = 1;
                        break;
                    case R.id.radioBasquete:
                        cod_esporte = 2;
                        break;
                    case R.id.radioTenis:
                        cod_esporte = 3;
                        break;
                    case R.id.radioTenisDeMesa:
                        cod_esporte = 4;
                        break;
                    case R.id.radioRugby:
                        cod_esporte = 5;
                        break;
                    case R.id.radioVolei:
                        cod_esporte = 6;
                        break;
                    case R.id.radioSurf:
                        cod_esporte = 7;
                        break;
                    case R.id.radioSkate:
                        cod_esporte = 8;
                        break;
                    case R.id.radioCorrida:
                        cod_esporte = 9;
                        break;

                }

                int selected = selectedId.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) selectedId.findViewById(selected);
                final String text = radioButton.getText().toString();



                //Função que controla o progressdialog e cadastra o usuário:
                progressDialog.setMessage("Criando evento, aguarde.");
                progressDialog.show();
                Runnable progressRunnable = new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.cancel();

                    }
                };

                Handler pdCanceller = new Handler();
                pdCanceller.postDelayed(progressRunnable, 3000);



                final int finalCod_esporte = cod_esporte;
                final int finalCod_esporte1 = cod_esporte;

                progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        progressDialog.dismiss();

                        ContentValues cv = new ContentValues();
                        cv.put(EventoContract.EventoEntry.TITULO_EVENTO, titulo);
                        cv.put(EventoContract.EventoEntry.ESPORTE, text);
                        cv.put(EventoContract.EventoEntry.LOCAL, local);
                        cv.put(EventoContract.EventoEntry.DT_EVENTO, dataEvento);
                        cv.put(EventoContract.EventoEntry.HR_INICIO, hrInicio);
                        cv.put(EventoContract.EventoEntry.HR_TERMINO, hrTermino);
                        cv.put(EventoContract.EventoEntry.DESCRICAO, descricao);
                        cv.put(EventoContract.EventoEntry.FK_ESPORTE, finalCod_esporte1);

                        snackBarSuccess(viewEvento, "Evento criado com sucesso!");

                        mDatabase.insert(EventoContract.EventoEntry.TABELA_EVENTO, null, cv);

                    }
                });

            }
        });

        return viewEvento;
    }

}
