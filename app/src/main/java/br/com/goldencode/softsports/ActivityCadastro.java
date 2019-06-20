package br.com.goldencode.softsports;

import android.app.ProgressDialog;
import android.content.ContentResolver;
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
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class ActivityCadastro extends AppCompatActivity{

    private AppCompatEditText editTextNome;
    private AppCompatEditText editTextSobrenome;
    private AppCompatEditText editTextEmail;
    private AppCompatEditText editTextSenha;
    private AppCompatButton buttonCadastrar;
    private AppCompatButton buttonPerfil;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private Uri FotoPerfilUri;
    private ImageView circleFotoPerfil;

    private static final int PICK_IMAGE_REQUEST = 1;

    private StorageReference mStorageRef;

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

        buttonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selecionarFoto();

            }
        });

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cadastrarUsuario();

            }
        });

    }

    private void selecionarFoto() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);

    }

    public void voltarLogin(View view){
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            FotoPerfilUri = data.getData();

                circleFotoPerfil.setImageURI(FotoPerfilUri);
                circleFotoPerfil.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                buttonPerfil.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_check_w));

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

    private void cadastrarUsuario(){

        String nome = editTextNome.getText().toString();
        String sobrenome = editTextSobrenome.getText().toString();
        String email = editTextEmail.getText().toString().trim();
        String senha = editTextSenha.getText().toString().trim();

        if (nome == null || TextUtils.isEmpty(nome)) {
            //se o campo senha estiver vazio
            Toast.makeText(this, "O campo nome está vazio", Toast.LENGTH_SHORT).show();
            //parar função
            return;
        }

        if (sobrenome == null || TextUtils.isEmpty(sobrenome)) {
            //se o campo senha estiver vazio
            Toast.makeText(this, "O campo sobrenome está vazio", Toast.LENGTH_SHORT).show();
            //parar função
            return;
        }

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
                            progressDialog.dismiss();
                            finish();
                            toastSuccess("Usuário cadastrado com sucesso!");
                        } else {
                            toastError("O usuário não pode ser cadastrado, tente novamente.");
                            progressDialog.dismiss();
                        }

                    }
                });

    }

}
