package com.pass.communify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ElectionActivity extends AppCompatActivity {
    Button botonComparte = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election);
        botonComparte = findViewById(R.id.button2);
        botonComparte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ElectionActivity.this, ComparteHogar.class);
                startActivity(intent);
            }
        });

    }

}