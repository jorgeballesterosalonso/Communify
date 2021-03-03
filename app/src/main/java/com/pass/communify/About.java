package com.pass.communify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import java.util.List;

public class About extends AppCompatActivity {
    private static WebView loadHTML;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        crearTabla();
        loadHTML = findViewById(R.id.wv_html);
        String html = crearTabla();
        loadHTML.loadData(html, "text/html", "UTF-8");
    }



    public static String crearTabla() {

      String html= "<html><head><title>About</title></head><body>";
        html += "<div><h1><u>Communify</u></h1></div><div><h2>Curso 2º Desarrollo de aplicaciones multiplataforma.</h2>" +
                "<h3>Asignatura: Desarrollo de interfaces. <br>Tutor: Ernesto Ramiro Cordoba</h3>" +
                "<h4>Integrantes del equipo: <br></h4> " +
                "<h4>Jorge Ballesteros<br>Xavier Robles<br>Alberto Garcia<br> Ivan Hernan<br> Marisa Fortea</h4> " +
                "<h4>Colaboradores especiales: Miguel parra</h4> " +
                "<h3>Communify@gmail.com</h3> " +
                "</div>";
        html +="</body></html>";

        return html;
    }
}