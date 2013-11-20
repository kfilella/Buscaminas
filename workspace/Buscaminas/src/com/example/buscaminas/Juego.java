package com.example.buscaminas;

import java.util.Timer;
import java.util.TimerTask;

import com.example.buscaminas.R;

import android.R.layout;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


public class Juego extends Activity {
	private TextView tiempo;
	private TextView puntaje;
	TableLayout tablero;
	private Celda matriz[][] = new Celda[32][32];
	private Celda n1;
	LinearLayout layouttiempo;
	Chronometer tiempo1;
	
	View[][] matrizCeldas = new View[32][32];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);
        
        layouttiempo=(LinearLayout)this.findViewById(R.id.layouttiempo);

        tiempo1 = new Chronometer(this);
        tiempo1.start();
        showElapsedTime();
        layouttiempo.addView(tiempo1);
        tiempo=(TextView)this.findViewById(R.id.tiempo);
        tiempo.setText("Tiempo:");
        tiempo.setTextColor(Color.GREEN);

        puntaje=(TextView)this.findViewById(R.id.puntaje);
        puntaje.setText("Bombas:");
        puntaje.setTextColor(Color.GREEN);
        
        tablero=(TableLayout)this.findViewById(R.id.tabla);
        
        
        //Typeface lcdFont = Typeface.createFromAsset(getAssets(),  "fonts/lcd2mono.ttf");
        //tiempo.setTypeface(lcdFont);
        //puntaje.setTypeface(lcdFont);
        
        for (int i = 0; i < 32; i++) {
        	TableRow fila = new TableRow(this);
        	for (int j=0; j<32; j++){
        		matriz[i][j] = new Celda(this,i,j,0);
        		//matriz[i][j].setOnClickListener(this);
        		matrizCeldas[i][j]= matriz[i][j];
        		fila.addView(matriz[i][j],40,40);
        	}
        	tablero.addView(fila);
    	}

    }
    public void onClick(View view) {
        ((Button) view).setText("*");
        ((Button) view).setEnabled(false);
    }
    private void showElapsedTime() {
        long elapsedMillis = SystemClock.elapsedRealtime() - tiempo1.getBase();            
        Toast.makeText(Juego.this, "Elapsed milliseconds: " + elapsedMillis, 
                Toast.LENGTH_SHORT).show();
    }
    
}