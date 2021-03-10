package com.pass.communify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_registrar;
    private TextInputEditText ti_correo;
    private TextInputEditText ti_pass;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn_registrar = findViewById(R.id.btn_login);
        ti_correo = findViewById(R.id.ti_correo2);
        ti_pass = findViewById(R.id.ti_pass2);
        findViewById(R.id.btn_login).setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        this.currentUser = currentUser;

    }

    //<---------------------------------Login Normal-----------------------------------------------<
    private void crearUsuario(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("prueba", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(RegisterActivity.this, ComparteHogar.class);
                            startActivity(intent);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("prueba", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                    }
                });
    }


    //>---------------------------------Fin Normal------------------------------------------------->
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String correolleno = ti_correo.getText().toString();
                String passlleno = ti_pass.getText().toString();
                Log.d("prueba", correolleno);
                Log.d("prueba", passlleno);
                if (!correolleno.equals("") && !passlleno.equals("")) {


                    if (currentUser == null) {
                        if (passlleno.length() >= 6) {
                            crearUsuario(correolleno, passlleno);

                        } else {
                            Toast.makeText(RegisterActivity.this, "La contraseña debe tener una longitud superior a 6 caracteres",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        Intent intent2 = new Intent(RegisterActivity.this, ComparteHogar.class);
                        startActivity(intent2);
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Email y contraseña no pueden estar vacios",
                            Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}