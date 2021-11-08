package com.practica.cifrador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnClickListener {

    private EditText entrada;
    private TextView salida;
    private Spinner spinner;
    private Button[] letras = new Button[27];
    private ImageButton bMayus;
    private boolean mayusClick = false, mayusLongClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entrada = (EditText) findViewById(R.id.entrada);
        salida = (TextView) findViewById(R.id.salida);

        spinner = (Spinner) findViewById(R.id.spinner);
        String[] desp = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, desp);
        spinner.setAdapter(adaptador);

        letras[0] = (Button) findViewById(R.id.a);
        letras[1] = (Button) findViewById(R.id.b);
        letras[2] = (Button) findViewById(R.id.c);
        letras[3] = (Button) findViewById(R.id.d);
        letras[4] = (Button) findViewById(R.id.e);
        letras[5] = (Button) findViewById(R.id.f);
        letras[6] = (Button) findViewById(R.id.g);
        letras[7] = (Button) findViewById(R.id.h);
        letras[8] = (Button) findViewById(R.id.i);
        letras[9] = (Button) findViewById(R.id.j);
        letras[10] = (Button) findViewById(R.id.k);
        letras[11] = (Button) findViewById(R.id.l);
        letras[12] = (Button) findViewById(R.id.m);
        letras[13] = (Button) findViewById(R.id.n);
        letras[14] = (Button) findViewById(R.id.ñ);
        letras[15] = (Button) findViewById(R.id.o);
        letras[16] = (Button) findViewById(R.id.p);
        letras[17] = (Button) findViewById(R.id.q);
        letras[18] = (Button) findViewById(R.id.r);
        letras[19] = (Button) findViewById(R.id.s);
        letras[20] = (Button) findViewById(R.id.t);
        letras[21] = (Button) findViewById(R.id.u);
        letras[22] = (Button) findViewById(R.id.v);
        letras[23] = (Button) findViewById(R.id.w);
        letras[24] = (Button) findViewById(R.id.x);
        letras[25] = (Button) findViewById(R.id.y);
        letras[26] = (Button) findViewById(R.id.z);

        bMayus = (ImageButton) findViewById(R.id.mayus);
        bMayus.setOnClickListener(this);
        bMayus.setOnLongClickListener(this);
    }

    public void botonera(View v) {
        int id;
        id = v.getId();
        Button button = (Button) findViewById(id);
        CharSequence ent = entrada.getText().append(button.getText());
        entrada.setText(ent);
        if (mayusClick) {
            setMinusculas();
            mayusClick = false;
        }
    }

    @Override
    public void onClick(View v) {
        if(mayusLongClick) {
            setMinusculas();
            mayusLongClick = false;
            mayusClick = false;
        }
        else {
            if(!mayusClick) {
                setMayusculas();
                mayusClick = true;
                return;
            }
            else {
                setMinusculas();
                mayusClick = false;
            }
        }
    }

    @Override
    public boolean onLongClick(View v) {
        setMayusculas();
        mayusLongClick = true;
        return true;
    }

    public void cifrar(View v) {
        int n = Integer.parseInt(spinner.getSelectedItem().toString());
        String texto = entrada.getText().toString();
        String cifrado = "";
        String alfabeto = "abcdefghijklmnñopqrstuvwxyz";
        String alfabetoMayus = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

        for (int i = 0; i < texto.length(); i++) {
            if(Character.isLowerCase(texto.charAt(i))) {
                int posicion = alfabeto.indexOf(texto.charAt(i));
                cifrado += alfabeto.charAt((posicion + n) % alfabeto.length());
            }
            else {
                int posicion = alfabetoMayus.indexOf(texto.charAt(i));
                cifrado += alfabetoMayus.charAt((posicion + n) % alfabetoMayus.length());
            }
        }

        salida.setText(cifrado);
    }

    public void descifrar(View v) {
        int n = Integer.parseInt(spinner.getSelectedItem().toString());
        String texto = entrada.getText().toString();
        String cifrado = "";
        String alfabeto = "abcdefghijklmnñopqrstuvwxyz";
        String alfabetoMayus = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

        for (int i = 0; i < texto.length(); i++) {
            if(Character.isLowerCase(texto.charAt(i))) {
                int posicion = alfabeto.indexOf(texto.charAt(i));
                if(posicion - n < 0) {
                    cifrado += alfabeto.charAt(27 + (posicion - n));
                }
                else {
                    cifrado += alfabeto.charAt(posicion - n);
                }
            }
            else {
                int posicion = alfabetoMayus.indexOf(texto.charAt(i));
                if(posicion - n < 0) {
                    cifrado += alfabetoMayus.charAt(27 + (posicion - n));
                }
                else {
                    cifrado += alfabetoMayus.charAt(posicion - n);
                }
            }
        }

        salida.setText(cifrado);
    }

    public void setMinusculas() {
        for (int i = 0; i < letras.length; i++) {
            CharSequence l = letras[i].getText();
            String aux = l.toString().toLowerCase();
            letras[i].setText(aux);
        }
    }

    public void setMayusculas() {
        for (int i = 0; i < letras.length; i++) {
            CharSequence l = letras[i].getText();
            String aux = l.toString().toUpperCase();
            letras[i].setText(aux);
        }
    }
}



























