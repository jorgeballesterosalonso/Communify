package com.pass.communify;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

/**
 * Esta es la clase
 */
public class ComparteHogar extends AppCompatActivity {
    ImageView imagenCocina = null;
    ImageView imagenBricolaje = null;
    ImageView imagenJardin = null;
    ImageView imagenMecanica = null;
    Button btnComparte = null;
    Button btnAtras = null;
    Chip chipHogar = null;

    ComparteHogar contexto = this;

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
        imagenCocina = findViewById(R.id.fotoCocina);
        imagenBricolaje = findViewById(R.id.fotoBricolaje);
        imagenJardin = findViewById(R.id.fotoJardin);
        imagenMecanica = findViewById(R.id.fotoMecanica);

        btnComparte = findViewById(R.id.buttonComparte);
        btnComparte.setEnabled(false);

        chipHogar = findViewById(R.id.chipHogar);

        /*
         *Creamos los OnClickListener para que el botón de Comparte se active para poder ser presionado
         */
        imagenBricolaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t = new Toast(contexto);
                t.setText("Has pulsado en el bricolaje");
                t.show();
                btnComparte.setEnabled(true);
                btnComparte.setActivated(false);
            }
        });
        imagenCocina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t = new Toast(contexto);
                t.setText("Has pulsado en la cocina");
                t.show();
                btnComparte.setEnabled(true);
            }
        });
        imagenMecanica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t = new Toast(contexto);
                t.setText("Has pulsado en la mecánica");
                t.show();
                btnComparte.setEnabled(true);
            }
        });
        imagenJardin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t = new Toast(contexto);
                t.setText("Has pulsado en el jardín");
                t.show();
                btnComparte.setEnabled(true);
            }
        });


    }
}