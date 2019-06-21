package br.com.goldencode.softsports;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    //EditTexts
    private AppCompatEditText editTextEmail;
    private AppCompatEditText editTextSenha;

    //Buttons
    private AppCompatButton buttonEntrar;
    private AppCompatButton buttonCadastrar;

    //Outros
    private ProgressDialog progressologin;
    private FirebaseAuth login;
    Softsports db = new Softsports(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatButton buttonEsqueciSenha = (AppCompatButton) findViewById(R.id.btnEsqueciSenha);
        buttonEsqueciSenha.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        editTextEmail = findViewById(R.id.edtEmail);
        editTextSenha = findViewById(R.id.edtSenha);
        buttonEntrar = findViewById(R.id.btnEntrar);

        //Onclick para acessar o app
        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Inicia a próxima atividade
                Intent intent = new Intent(MainActivity.this, TelaInicialActivity.class);
                startActivity(intent);
            }
        });

        /* TESTE CRUD */


    }

    //Abre a atividade de cadastro
    public void abrirActivityCadastro(View view){
        Intent intent = new Intent(this, ActivityCadastro.class);
        startActivity(intent);
    }

}
