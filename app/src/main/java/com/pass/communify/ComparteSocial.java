package com.pass.communify;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Esta es la clase
 */
public class ComparteSocial extends AppCompatActivity implements
        View.OnClickListener {
    ComparteSocial contexto = this;
    private TextView nombre;
    private GoogleSignInClient mGoogleSignInClient;//Google
    private final String TAG = "SignInActivity";//Google
    private final int RC_SIGN_IN = 9001; //Google
    private TextView mStatusTextView; //Google
    private TextView name; //Pruebas de boton del modal
    private GoogleSignInAccount account;
    private Button btnSolicita;

    LoginActivity loginCursor = new LoginActivity(); //Puebas objeto login, llamada de metodos
    Button btnComparte = null;

    /**
     * Método de creación del Activity ComparteHogar
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparte_social);

        //Inicializamos variables

        btnComparte = findViewById(R.id.buttonComparte);
        btnComparte.setEnabled(false);
        btnSolicita = findViewById(R.id.buttonSolicita);
        btnSolicita.setEnabled(false);
//>---------------------------------Googgle-------------------------------------------------------->
        name = findViewById(R.id.name);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        //mStatusTextView = findViewById(R.id.tv_sign_status);
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//<---------------------------------Googgle--------------------------------------------------------<
        /*
         *Creamos los OnClickListener para que el botón de Comparte se active para poder ser presionado
         */
        findViewById(R.id.buttonComparte).setOnClickListener(this);
        findViewById(R.id.buttonSolicita).setOnClickListener(this);
        findViewById(R.id.fotoAbuelos).setOnClickListener(this);
        findViewById(R.id.fotoAmigos).setOnClickListener(this);
        findViewById(R.id.fotoCompra).setOnClickListener(this);
        findViewById(R.id.fotoFiesta).setOnClickListener(this);
        findViewById(R.id.chipHogar).setOnClickListener(this);
        findViewById(R.id.chipSocial).setOnClickListener(this);
        findViewById(R.id.chipOtros).setOnClickListener(this);
        findViewById(R.id.fab).setOnClickListener(this);
    }
    //>---------------------------------Googgle-------------------------------------------------------->

    /**
     * Compruebe la cuenta de inicio de sesión de Google existente, si el usuario ya ha iniciado sesión
     */
    @Override
    public void onStart() {
        super.onStart();
        // INICIO on_start_sign_in
        // Compruebe la cuenta de inicio de sesión de Google existente, si el usuario ya ha iniciado sesión
        // GoogleSignInAccount no será nulo.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
        // END on_start_sign_in
    }

    // INICIO en Resultado de actividad

    /**
     * Conecta con Google y muestra las cuentas del usuario
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Resultado devuelto al iniciar Intent desde GoogleSignInClient.getSignInIntent (...);
        if (requestCode == RC_SIGN_IN) {
            // La tarea devuelta de esta llamada siempre se completa, no es necesario adjuntar
            // un oyente
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    /**
     * @param completedTask devuele true o false si se registra con exito
     */
    //FIN del resultado de la actividad
    //INICIO manejar Inicio sesión del Resultado
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Se registró correctamente, muestra la interfaz de usuario autenticada.
            updateUI(account);
        } catch (ApiException e) {
            // El código de estado de ApiException indica el motivo detallado del error.
            // Consulte la referencia de la clase GoogleSignInStatusCodes para obtener más información.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    /**
     * Cierra sesion de google
     */
    // Final
    // Inicio acceso revocado
    public void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // Inicio Excluido
                        updateUI(null);
                        // Fin Excluido
                    }
                });
    }

    /**
     * @param account le pasa los datos a ala cuenta si se ha inciado o no la sesion.
     *                comparamos resultado y realizamos acciones segun necesitemos
     */
    // Fin acceso revocado
    public void updateUI(@Nullable GoogleSignInAccount account) {

        if (account != null) {
//Setea una variable global con el nombre
            // mStatusTextView.setText(getString(R.string.signed_in_fmt, account.getDisplayName()));
            //name.setText(getString(R.string.signed_in_fmt, account.getDisplayName()));
            this.account = account;
        } else {
            // ((TextView) findViewById(R.id.tv_sign_status)).setText(R.string.signed_in_err);
        }
    }
//<---------------------------------Googgle--------------------------------------------------------<
//>---------------------------------Menu AppBa----------------------------------------------------->

    /**
     * @param menu Boton de menu, los 3  puntitos a la derecha
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; Esto añade el boton del menu a la appBar
        getMenuInflater().inflate(R.menu.menu_contex, menu);
        return true;
    }

    /**
     * @param item objetos declarado en menu_contex
     * @return devuele el metodo segun elboton seleccionado
     * @see menu_contex.xml
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Setting) {
            Toast toast = Toast.makeText(this, "Proximamente", Toast.LENGTH_LONG);
            toast.show();
            return true;
        }
        if (id == R.id.Log_off) {
            Toast toast = Toast.makeText(this, "Cerrando Sesion", Toast.LENGTH_LONG);
            toast.show();
            revokeAccess();
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(ComparteSocial.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.Close) {
            Toast toast = Toast.makeText(this, "Cerrando aplicacion", Toast.LENGTH_LONG);
            toast.show();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        if (id == R.id.user) {

            if (account != null) {
                showAlertDialogButtonClicked(ComparteSocial.this);
                Toast toast = Toast.makeText(this, "Usuario conectado", Toast.LENGTH_LONG);
                toast.show();
            } else {
                Toast toast2 = Toast.makeText(this, "Solo con google", Toast.LENGTH_LONG);
                toast2.show();
            }


            return true;
        }
        if (id == R.id.compartir) {
            Toast toast = Toast.makeText(this, "Compartir", Toast.LENGTH_LONG);
            toast.show();

            return true;
        }
        if (id == R.id.chatbox) {
            Toast toast = Toast.makeText(this, "ChatBox", Toast.LENGTH_LONG);
            toast.show();
            //poner aqui el intent para el chatbox
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
//<---------------------------------Menu AppBa-----------------------------------------------------<
//>---------------------------------Modal---------------------------------------------------------->

    /**
     * @param view lanza el modal con la siguentes opciones y botones
     */
    public void showAlertDialogButtonClicked(ComparteSocial view) {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View vista = getLayoutInflater().inflate(R.layout.alertdialog_view, null);
        TextView tv = vista.findViewById(R.id.name);

        tv.setText(getString(R.string.signed_in_fmt, account.getDisplayName()));

        builder.setView(vista);
        // add the buttons
        builder.setPositiveButton("name", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do something like...
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("About", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do something like...
                Intent intent = new Intent(ComparteSocial.this, About.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        builder.setNeutralButton("#Communify", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do something like...
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
//<---------------------------------Modal----------------------------------------------------------<
//>---------------------------------Botones-------------------------------------------------------->

    /**
     * Switch de todos lo botones del Activity
     *
     * @param v
     */
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonComparte:

                break;
            case R.id.buttonSolicita:
                Intent intentMaps = new Intent(ComparteSocial.this, MapsActivity.class);
                startActivity(intentMaps);
                break;

            case R.id.fotoCompra:
                Toast t_Cocina = new Toast(contexto);
                t_Cocina.setText("Has pulsado en la compra");
                t_Cocina.show();
                btnComparte.setEnabled(true);
                break;

            case R.id.fotoAbuelos:
                Toast t_Bricolaje = new Toast(contexto);
                t_Bricolaje.setText("Has pulsado en los abuelos");
                t_Bricolaje.show();
                btnComparte.setEnabled(true);
                btnComparte.setActivated(false);
                break;
            case R.id.fotoAmigos:
                Toast t_Jardin = new Toast(contexto);
                t_Jardin.setText("Has pulsado en los amigos");
                t_Jardin.show();
                btnComparte.setEnabled(false);
                break;

            case R.id.fotoFiesta:
                Toast t_Mecanica = new Toast(contexto);
                t_Mecanica.setText("Has pulsado en la fiesta");
                t_Mecanica.show();
                btnComparte.setEnabled(true);
                break;

            case R.id.chipHogar:
                Intent intentHogar = new Intent(ComparteSocial.this, ComparteHogar.class);
                startActivity(intentHogar);
                break;
            case R.id.chipSocial:

                break;
            case R.id.chipOtros:

                break;
            case R.id.fab:
                Intent intentGlobal = new Intent(ComparteSocial.this, MapsActivity.class);
                startActivity(intentGlobal);
                break;
        }
    }
//<---------------------------------Modal----------------------------------------------------------<
}