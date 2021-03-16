package com.pass.communify;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
public class ComparteHogar extends AppCompatActivity implements
        View.OnClickListener {
    ComparteHogar contexto = this;
    private TextView nombre;
    private GoogleSignInClient mGoogleSignInClient;//Google
    private final String TAG = "SignInActivity";//Google
    private final int RC_SIGN_IN = 9001; //Google
    private TextView mStatusTextView; //Google
    private TextView name; //Pruebas de boton del modal
    private GoogleSignInAccount account;
    private Button btnSolicita;
    private Categoria categoria;
    private ImageView imagenCocina;
    private ImageView imagenBricolaje;
    private ImageView imagenJardin;
    private ImageView imagenMecanica;

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
        setContentView(R.layout.activity_comparte_hogar);

        //Inicializamos variables

        btnComparte = findViewById(R.id.buttonComparte);
        btnComparte.setEnabled(false);
        btnSolicita = findViewById(R.id.buttonSolicita);
        btnSolicita.setEnabled(false);
        imagenJardin = findViewById(R.id.fotoJardin);
        imagenMecanica = findViewById(R.id.fotoMecanica);
        imagenCocina = findViewById(R.id.fotoCocina);
        imagenBricolaje = findViewById(R.id.fotoBricolaje);
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
        findViewById(R.id.fotoCocina).setOnClickListener(this);
        findViewById(R.id.fotoBricolaje).setOnClickListener(this);
        findViewById(R.id.fotoJardin).setOnClickListener(this);
        findViewById(R.id.fotoMecanica).setOnClickListener(this);
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
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Setting) {
            //Toast toast = Toast.makeText(this, "Proximamente", Toast.LENGTH_LONG);
            //toast.show();
            Intent intent = new Intent(ComparteHogar.this, ConfiguracionActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.Log_off) {
            Toast toast = Toast.makeText(this, "Cerrando Sesion", Toast.LENGTH_LONG);
            toast.show();
            revokeAccess();
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(ComparteHogar.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.Close) {
            //Toast toast = Toast.makeText(this, "Cerrando aplicacion", Toast.LENGTH_LONG);
            //toast.show();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        if (id == R.id.user) {

            if (account != null) {
                showAlertDialogButtonClicked(ComparteHogar.this);
                Toast toast = Toast.makeText(this, "Usuario conectado", Toast.LENGTH_LONG);
                toast.show();
            } else {
                Toast toast2 = Toast.makeText(this, "Solo con google", Toast.LENGTH_LONG);
                toast2.show();
            }


            return true;
        }
        if (id == R.id.compartir) {
            //Toast toast = Toast.makeText(this, "Compartir", Toast.LENGTH_LONG);
            //toast.show();


            Intent compartir = new Intent(android.content.Intent.ACTION_SEND);
            compartir.setType("text/plain");
            //String mensaje = R.string.compartir;
            compartir.putExtra(android.content.Intent.EXTRA_SUBJECT, "Communify App");
            compartir.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.Compartir));
            startActivity(Intent.createChooser(compartir, "Compartir vía"));


            return true;
        }
        if (id == R.id.chatbox) {
            //Toast toast = Toast.makeText(this, "ChatBox", Toast.LENGTH_LONG);
            //toast.show();
            //poner aqui el intent para el chatbox
            Intent intent = new Intent(ComparteHogar.this, ChatBot.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
//<---------------------------------Menu AppBa-----------------------------------------------------<
//>---------------------------------Modal---------------------------------------------------------->

    /**
     * @param view lanza el modal con la siguentes opciones y botones
     */
    public void showAlertDialogButtonClicked(ComparteHogar view) {
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
                Intent intent = new Intent(ComparteHogar.this, About.class);
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
                Intent intent = new Intent(ComparteHogar.this, AniadirProducto.class);
                intent.putExtra("categoría", categoria);
                startActivity(intent);

                break;
            case R.id.buttonSolicita:
                Intent intentMaps = new Intent(ComparteHogar.this, MapsActivity.class);
                intentMaps.putExtra("categoria", categoria);
                startActivity(intentMaps);
                break;

            case R.id.fotoCocina:

                btnComparte.setEnabled(true);
                btnSolicita.setEnabled(true);
                categoria = Categoria.COCINA;
                imagenCocina.setColorFilter(R.color.Communify_White);
                imagenBricolaje.setColorFilter(null);
                imagenMecanica.setColorFilter(null);
                imagenJardin.setColorFilter(null);
                break;

            case R.id.fotoBricolaje:

                btnComparte.setEnabled(true);
                btnSolicita.setEnabled(true);
                categoria = Categoria.BRICOLAJE;
                imagenBricolaje.setColorFilter(R.color.Communify_White);
                imagenCocina.setColorFilter(null);
                imagenMecanica.setColorFilter(null);
                imagenJardin.setColorFilter(null);
                break;
            case R.id.fotoJardin:

                btnComparte.setEnabled(true);
                btnSolicita.setEnabled(true);
                categoria = Categoria.JARDIN;
                imagenJardin.setColorFilter(R.color.Communify_White);
                imagenBricolaje.setColorFilter(null);
                imagenMecanica.setColorFilter(null);
                imagenCocina.setColorFilter(null);
                break;

            case R.id.fotoMecanica:

                btnComparte.setEnabled(true);
                btnSolicita.setEnabled(true);
                categoria = Categoria.MECANICA;
                imagenMecanica.setColorFilter(R.color.Communify_White);
                imagenBricolaje.setColorFilter(null);
                imagenCocina.setColorFilter(null);
                imagenJardin.setColorFilter(null);
                break;

            case R.id.chipHogar:
                Toast t = new Toast(contexto);
                t.setText("Ya estás en la pantalla de 'Hogar'");
                t.show();
                break;
            case R.id.chipSocial:
                Intent intentSocial = new Intent(ComparteHogar.this, ComparteSocial.class);
                startActivity(intentSocial);
                break;
            case R.id.chipOtros:

                break;
            case R.id.fab:
                Intent intentGlobal = new Intent(ComparteHogar.this, MapsActivity.class);
                startActivity(intentGlobal);
                break;
        }
    }
//<---------------------------------Modal----------------------------------------------------------<
}