package br.com.goldencode.softsports;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityCadastro extends AppCompatActivity implements View.OnClickListener{

    private AppCompatEditText editTextNome;
    private AppCompatEditText editTextSobrenome;
    private AppCompatEditText editTextEmail;
    private AppCompatEditText editTextSenha;
    private AppCompatEditText editTextSenhaConfirmada;
    private AppCompatButton buttonCadastrar;
    private AppCompatButton buttonPerfil;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private Uri mSelectedUri;
    private ImageView circleFotoPerfil;
    private ImageView circleBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this, R.style.customAlertDialogStyle);

        editTextNome = (AppCompatEditText) findViewById(R.id.edtNome);
        editTextSobrenome = (AppCompatEditText) findViewById(R.id.edtSobrenome);
        editTextEmail = (AppCompatEditText) findViewById(R.id.edtEmail);
        editTextSenha = (AppCompatEditText) findViewById(R.id.edtSenha);
        buttonCadastrar = (AppCompatButton) findViewById(R.id.btnCadastrar);
        buttonPerfil = (AppCompatButton) findViewById(R.id.btnFoto);
        circleFotoPerfil = (ImageView) findViewById(R.id.iconePerfil);
        circleBackground = (ImageView) findViewById(R.id.circleBackground);

        buttonPerfil.setOnClickListener(this);
        buttonCadastrar.setOnClickListener(this);

    }

    private void cadastrarUsuario(){

        String email = editTextEmail.getText().toString().trim();
        String senha = editTextSenha.getText().toString().trim();

        if (email == null || TextUtils.isEmpty(email)) {
            //se o campo email estiver vazio
            Toast.makeText(this, "O campo email está vazio", Toast.LENGTH_SHORT).show();
            //parar função
            return;
        }

        if (senha == null || TextUtils.isEmpty(senha)) {
            //se o campo senha estiver vazio
            Toast.makeText(this, "O campo senha está vazio", Toast.LENGTH_SHORT).show();
            //parar função
            return;
        }

        //se as validações estiverem ok
        //será mostrado o progressdialog

        progressDialog.setMessage("Cadastrando usuário, aguarde.");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //se o usuário for registrado com sucesso
                        if(task.isSuccessful()){
                            toastSuccess("Usuário cadastrado com sucesso!");
                            progressDialog.dismiss();
                            finish();
                        } else {
                            toastError("O usuário não pode ser cadastrado, tente novamente.");
                            progressDialog.dismiss();
                        }

                    }
                });

    }

    public void voltarLogin(View view){
        finish();
    }

    @Override
    public void onClick(View v) {

        if (v == buttonCadastrar) {
            cadastrarUsuario();
        }

        if (v == buttonPerfil) {
            selecionarFoto();
        }

    }

    private void selecionarFoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            assert data != null;
            mSelectedUri = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mSelectedUri);
                circleFotoPerfil.setImageDrawable(new BitmapDrawable(bitmap));
                circleFotoPerfil.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                buttonPerfil.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_check_w));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void toastSuccess(String mensagem){

        Context context = getApplicationContext();
        CharSequence text = mensagem;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);

        View view = toast.getView();

        //Obtém o plano de fundo oval real do Toast e, em seguida, define o filtro de cores
        view.getBackground().setColorFilter(getResources().getColor(R.color.colorSuccess), PorterDuff.Mode.SRC_IN);

        //Obtém o TextView do Toast para que ele possa ser editado
        TextView newText = view.findViewById(android.R.id.message);
        newText.setShadowLayer(0, 0, 0, Color.TRANSPARENT);
        newText.setTextColor(getResources().getColor(R.color.colorWhite));

        toast.show();

    }

    public void toastError(String mensagem){

        Context context = getApplicationContext();
        CharSequence text = mensagem;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);

        View view = toast.getView();

        //Obtém o plano de fundo oval real do Toast e, em seguida, define o filtro de cores
        view.getBackground().setColorFilter(getResources().getColor(R.color.colorError), PorterDuff.Mode.SRC_IN);

        //Obtém o TextView do Toast para que ele possa ser editado
        TextView newText = view.findViewById(android.R.id.message);
        newText.setShadowLayer(0, 0, 0, Color.TRANSPARENT);
        newText.setTextColor(getResources().getColor(R.color.colorWhite));

        toast.show();

    }

}
