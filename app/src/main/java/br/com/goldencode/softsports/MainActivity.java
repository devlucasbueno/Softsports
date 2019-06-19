package br.com.goldencode.softsports;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppCompatEditText editTextEmail;
    private AppCompatEditText editTextSenha;
    private AppCompatButton buttonEntrar;
    private AppCompatButton buttonCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatButton buttonEsqueciSenha = (AppCompatButton) findViewById(R.id.btnEsqueciSenha);
        buttonEsqueciSenha.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        editTextEmail = (AppCompatEditText) findViewById(R.id.edtEmail);
        editTextSenha = (AppCompatEditText) findViewById(R.id.edtSenha);
        buttonEntrar = (AppCompatButton) findViewById(R.id.btnEntrar);



    }

    public void abrirActivityCadastro(View view){
        Intent intent = new Intent(this, ActivityCadastro.class);
        startActivity(intent);
    }

    public void abrirActivityInicio(View view){
        Intent intent = new Intent(this, TelaInicialActivity.class);
        startActivity(intent);
    }

}
