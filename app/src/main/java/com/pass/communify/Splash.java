package com.pass.communify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

/**
 * @author Comunify
 * @author Xavier R, Jorge B, Alberto G, Ivan, Marisa.
 * @version 0.01
 * @see MainActivity
 * @see anim\alpha.xml
 * @see layout\activity_splash.xml
 */
public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        /**
         * Llamada al metodo para cambiar de forma automatica de pantalla
         * poner True/False segun se requiera.
         * @see Metodo openApp
         */
        openApp(true);
        /**
         @Animacion: Efecto de CrossFade del logo al iniciar la aplicacion,
         las opciones comentadas son para poner colores o mensajes si no carga el logo,
         recortar la imagen dando un efecto de circulo
         **/
        ImageView fotoLogo = findViewById(R.id.iv_Logo_Splash);
        Animation animacionAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        fotoLogo.startAnimation(animacionAlpha);
        Glide.with(this)
                .load(R.drawable.logotest)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .placeholder(new ColorDrawable(this.getResources() //colo si no carga el logo
                        .getColor(R.color.design_default_color_background))) //color por defecto
                .circleCrop() //forma de circulo
                //.getDiskCacheStrategy(DiskCacheStrategy.NONE) //opciones para caches de glide
                .into(fotoLogo);


        /**
         @Animacion: Efecto de CrossFade del mensaje al iniciar la aplicacion,
         las opciones comentadas son para poner colores o mensajes.
         **/

        TextView bienvenida = findViewById(R.id.tv_bienvenida);
        Animation animacionAlphaTextoBienvenida = AnimationUtils.loadAnimation(this, R.anim.alpha2);
        bienvenida.startAnimation(animacionAlphaTextoBienvenida);
        Glide.with(this)
                .load(bienvenida)
                //.centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade(500));
        //.placeholder(new ColorDrawable(this.getResources() //colo si no carga el logo
        //.getColor(R.color.design_default_color_background))) //color por defecto
        //.circleCrop() //forma de circulo
        //.getDiskCacheStrategy(DiskCacheStrategy.NONE) //opciones para caches de glide

    }

    /**
     * @param locationPermission Metodo para que cambien la pantalla de forma automatica cada 8 segundos (modificable)
     *                           cambiar el Intent para selecionar la pantalla a la que cambiara.
     */
    private void openApp(boolean locationPermission) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash
                        .this, LoginActivity.class); //Cambiar aqui para que pase a la nueva pantalla
                startActivity(intent);
                finish();
            }
        }, 8000); //Duracion entre el cambio de pantallas
    }

}