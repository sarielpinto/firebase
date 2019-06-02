package com.example.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText inputEmail;
    private Button btnReset, btnBack;
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        inputEmail = (EditText) findViewById(R.id.email);
        btnReset = (Button) findViewById(R.id.btn_reset_password);
        btnBack = (Button) findViewById(R.id.btn_back);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        auth=FirebaseAuth.getInstance();
/*
PARA EL MAINACTIVITY.JAVA

COMPROBANDO LA SESION DEL USUARIO
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() !=null){
            //User is logged in
        }
CAMBIAR CONTRASEÑA
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.updatePassword(newPassword.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"La contraseña esta actualizada!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Error al actualizar la contraseña!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        CAMBIAR EMAIL
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

        user.updateEmail(newEmail.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"La dirección de correo electrónico está actualizada.",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Error al actualizar el correo electrónico!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

  ELIMINAR CUENTO/USUARIO
FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
if (user != null){
    user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            if(task.isSuccessful()){
                Toast.makeText(MainActivity.this, "Su perfil ha sido eliminado:(Crear una cuenta ahora!)", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity.this,"Error al eliminar tu cuenta!",Toast.LENGTH_SHORT).show();
            }
        }
    });
}
DESCONECTAR
auth.signOut();

FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user= firebaseAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
            finish();
        }
    }
};
*/
        btnBack.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplication(),"Ingrese su ID de correo electrónico registrada",Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ResetPasswordActivity.this,"Te hemos enviado instrucciones para restablecer tu contraseña.!",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(ResetPasswordActivity.this,"Error al enviar correo electronico de restablecimiento!",Toast.LENGTH_SHORT).show();
                        }

                        progressBar.setVisibility(View.GONE);
                    }
                });
            }

        });
    }
}
