package com.example.buscaminas;

import java.util.Timer;
import java.util.TimerTask;

import com.example.buscaminas.R;

import android.R.layout;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class Juego extends Activity {
	private TextView tiempo;
	private TextView puntaje;
	TableLayout tablero;
	private Celda matriz[][] = new Celda[7][7];
	private Celda n1;
	
	View[][] matrizCeldas = new View[7][7];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);
        tiempo=(TextView)this.findViewById(R.id.tiempo);
        tiempo.setText("00:00");
        tiempo.setTextColor(Color.GREEN);

        puntaje=(TextView)this.findViewById(R.id.puntaje);
        puntaje.setText("PUNTAJE");
        puntaje.setTextColor(Color.GREEN);
        tablero=(TableLayout)this.findViewById(R.id.tabla);
        
        
        //Typeface lcdFont = Typeface.createFromAsset(getAssets(),  "fonts/lcd2mono.ttf");
        //tiempo.setTypeface(lcdFont);
        //puntaje.setTypeface(lcdFont);
        
        for (int i = 0; i < 7; i++) {
        	TableRow fila = new TableRow(this);
        	for (int j=0; j<7; j++){
        		matriz[i][j] = new Celda(this,i,j,0);
        		matrizCeldas[i][j]= matriz[i][j];
        		fila.addView(matrizCeldas[i][j]);
        	}
        	tablero.addView(fila);
    	}

    }
}