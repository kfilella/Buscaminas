package com.example.buscaminas;

import com.example.buscaminas.R;

import android.R.layout;
import android.app.Activity;
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
	private celda bloque[][];
	private celda n1;
	
	View[] buttonsAndEditboxes = new View[7];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);
        tiempo=(TextView)this.findViewById(R.id.tiempo);
        puntaje=(TextView)this.findViewById(R.id.puntaje);
        tablero=(TableLayout)this.findViewById(R.id.tabla);
        
        //Typeface lcdFont = Typeface.createFromAsset(getAssets(),  "fonts/lcd2mono.ttf");
        //tiempo.setTypeface(lcdFont);
        //puntaje.setTypeface(lcdFont);
        
        for (int i = 0; i < 7; i++) {
        	   
        	        buttonsAndEditboxes[i]= new Button(this);
        	      
        	}
        
        for (int i = 0; i < 7; i++) {
        	   tablero.addView(buttonsAndEditboxes[i]);
        	}
        		
        
    }
}