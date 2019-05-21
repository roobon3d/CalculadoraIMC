package basico.android.cftic.edu.micalculoimc;

import android.content.Intent;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saquito) {
        super.onCreate(saquito);
        setContentView(R.layout.activity_main);

        if (saquito == null) {
            Log.d("MIAPP", "Es la primera vez que se ejecuta o no hay nada guardado");

        } else {
            Log.d("MIAPP", "Hay cosas guardadas");
            boolean valor_guardado = saquito.getBoolean("GUARDADO");
            Log.d("MIAPP", "Valor guardado" + valor_guardado);

            float peso = saquito.getFloat("PESO_GUARDADO");
            float altura = saquito.getFloat("ALTURA_GUARDADA");

            float imcCalculado;


            imcCalculado = calcularIMC(peso, altura);
            representaDatos(imcCalculado);

        }

    }

    //pedirPeso

    private float pedirPeso() {

        float peso = 1;

        EditText editTextPeso = findViewById(R.id.entradaPeso);
        String pesoIntroducido = editTextPeso.getText().toString(); // leo el contenido

        peso = Float.parseFloat(pesoIntroducido);

        Log.d("MIAPP", "texto peso = " + peso);

        return peso;
    }


    //pedirAltura
    private float pedirAltura() {

        float altura = 1;

        EditText editTextAltura = findViewById(R.id.entradaAltura);
        String alturaIntroducida = editTextAltura.getText().toString(); // leo el contenido

        altura = Float.parseFloat(alturaIntroducida);

        Log.d("MIAPP", "texto altura = " + altura);

        return altura;
    }

//calcularIMC

    private float calcularIMC(float peso, float altura) {

        float imc = 0;

        imc = peso / (altura * altura);

        return imc;

    }


    public void representaDatos(float imcCalculado) {
        //informarIMC


        TextView destino_IMC = findViewById(R.id.destinoIMC);
        destino_IMC.setText(String.valueOf(imcCalculado));


        if (imcCalculado < 16) {
            TextView destino_Salud = findViewById(R.id.destino_estadoSalud);
            destino_Salud.setText(getResources().getString(R.string.desnutrido)); // accedemos a un string en resources del string para tenerlo en varios idiomas
            ImageView imageView = findViewById(R.id.imagenEstado);
            imageView.setImageResource(R.drawable.desnutrido);

        } else if (imcCalculado < 18) {
            TextView destino_Salud = findViewById(R.id.destino_estadoSalud);
            destino_Salud.setText(getResources().getString(R.string.delgado)); // accedemos a un string en resources del string para tenerlo en varios idiomas
            ImageView imageView = findViewById(R.id.imagenEstado);
            imageView.setImageResource(R.drawable.delgado);
        } else if (imcCalculado < 25) {
            TextView destino_Salud = findViewById(R.id.destino_estadoSalud);
            destino_Salud.setText(getResources().getString(R.string.ideal)); // accedemos a un string en resources del string para tenerlo en varios idiomas
            ImageView imageView = findViewById(R.id.imagenEstado);
            imageView.setImageResource(R.drawable.ideal);
        } else if (imcCalculado < 31) {
            TextView destino_Salud = findViewById(R.id.destino_estadoSalud);

            destino_Salud.setText(getResources().getString(R.string.sobrepeso)); // accedemos a un string en resources del string para tenerlo en varios idiomas

            ImageView imageView = findViewById(R.id.imagenEstado);
            imageView.setImageResource(R.drawable.sobrepeso);
        } else {
            TextView destino_Salud = findViewById(R.id.destino_estadoSalud);
            destino_Salud.setText(getResources().getString(R.string.obeso)); // accedemos a un string en resources del string para tenerlo en varios idiomas
            ImageView imageView = findViewById(R.id.imagenEstado);
            imageView.setImageResource(R.drawable.obeso);
        }

    }

    public void botonCalculoIMC(View view) {
//TODO Gestionar el evento del boton


        float peso;
        float altura;
        float imcCalculado;

        peso = pedirPeso();
        altura = pedirAltura();

        imcCalculado = calcularIMC(peso, altura);


        representaDatos(imcCalculado);


    }

    // ************ APPBAR *************

    // DIBUJO EL MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    //RECIBIR EL EVENTO DEL MENU QUE SE HA PULSADO
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.info: //info está en menu.xml

                Log.d("MIAPP", "tocó Informacion");

                abrirPaginaWeb("https://es.wikipedia.org/wiki/Índice_de_masa_corporal");

                break;

            default:
                Log.d("MIAPP", "tocó id desconocido");
        }

        return super.onOptionsItemSelected(item);
    }


    public void abrirPaginaWeb(String url) {

        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle saquito) {
        super.onSaveInstanceState(saquito);

        Log.d("MIAPP", "La actividad se va a recrear");
        saquito.putBoolean("CARGADA", true);

        float peso = 1;
        EditText editTextPeso = findViewById(R.id.entradaPeso);
        String pesoIntroducido = editTextPeso.getText().toString(); // leo el contenido

        peso = Float.parseFloat(pesoIntroducido);

        saquito.putFloat("PESO_GUARDADO", peso);

        float altura = 1;

        EditText editTextAltura = findViewById(R.id.entradaAltura);
        String alturaIntroducida = editTextAltura.getText().toString(); // leo el contenido

        altura = Float.parseFloat(alturaIntroducida);

        saquito.putFloat("ALTURA_GUARDADA", altura);


    }


}


