package com.pass.communify;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class AniadirProducto extends AppCompatActivity implements Serializable {
    private ImageView imagen;
    private Context context;
    private Uri imageUri;
    private Button btnAniadir;
    private EditText etTitulo;
    private EditText etDescripcion;
    private String categoría;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir_producto);
        imagen = findViewById(R.id.imagenProducto);
        etTitulo = findViewById(R.id.etTitulo);
        etDescripcion = findViewById(R.id.DescripcionProducto);
        Intent intent = getIntent();

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);


            }
        });
        btnAniadir = findViewById(R.id.btnAniadir);
        btnAniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto p = new Producto(etTitulo.getText().toString(), etDescripcion.getText().toString(), imageUri, true, (Categoria) intent.getSerializableExtra("categoría"));
                Log.d("TAG", p.toString());
            }
        });
    }

    /**
     * Este método es para la respuesta de la elección de la imagen, setea el ImageView con la imagen seleccionada
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (null != data) {
            imageUri = data.getData();
            Log.d("URI", imageUri.toString());
            imagen.setImageURI(imageUri);
            //Do whatever that you desire here. or leave this blank

        }

    }

}