package com.example.buscaminas;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.example.buscaminas.R;
import com.example.buscaminas.R.drawable;

import android.R.layout;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


public class Juego extends Activity {
	private TextView tiempo;
	private TextView puntaje;
	TableLayout tablero;
	private Celda n1;
	Buscaminas b=new Buscaminas();
	LinearLayout layouttiempo;
	Chronometer tiempo1;
	int ancho=0,alto=0;
	int minas=0;
	
	
	
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);
        Bundle datos = this.getIntent().getExtras();
        ancho=datos.getInt("dif");
        alto=datos.getInt("dif1");
        minas=datos.getInt("minas");
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
        Celda matriz[][] = new Celda[ancho][alto];
        int matrizminas[][] = new int[ancho][alto];
       
        for (int i = 0; i < ancho; i++) {
        	for (int j=0; j<alto; j++){
        		matrizminas[i][j]=0;
        	}
        	}
       
        Random r = new Random();
        for(int l=0; l<minas; l++ ){
        int i=r.nextInt(ancho);
        int j=r.nextInt(alto);
        if(matrizminas[i][j]!=11)     matrizminas[i][j]=11;
        else
        	l++;
        }
        
        for (int i = 0; i < ancho; i++) {
        	for (int j=0; j<alto; j++){
        		int cont=0;
        		if(matrizminas[i][j]!=11){
        			if(i>=1 && (j+1)<alto && j>=1){
        				if(matrizminas[i-1][j-1]==11)
        			   cont++;
        				if(matrizminas[i-1][j]==11)
        				cont++;
        				if(matrizminas[i-1][j+1]==11)
        				cont++;
        			
        		}
        		if(j>1  && (i+1)<ancho ){
        			
         			if(matrizminas[i][j-1]==11)
         				cont++;
         			if(matrizminas[i+1][j-1]==11)
         				cont++;
        			
        		}
        		if((i+1)<ancho && (j+1)<alto){
        			if(matrizminas[i+1][j]==11)
         				cont++;
         			if(matrizminas[i+1][j+1]==11)
         				cont++;
        			
        		}
        		if((j+1)<alto  && (i+1)<ancho ){
        			if(matrizminas[i][j+1]==11)
         				cont++;
         			
        			
        		}
        		matrizminas[i][j]=cont;
        	}
        		
        }
       }
        
        
        
        for (int i = 0; i < ancho; i++) {
        	TableRow fila = new TableRow(this);
        	for (int j=0; j<alto; j++){
        		final Celda c =  new Celda(this,i,j,0);	
        		 c.setOnTouchListener(new OnTouchListener() {
 					
 					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						ImageButton cara = (ImageButton) findViewById(R.id.imagecara);
 						if(arg1.getAction() == MotionEvent.ACTION_DOWN){
 							cara.setPressed(true);
 						}else //if(event.getAction() == MotionEvent.ACTION_UP)
 							cara.setPressed(false);
 						return false;
						
					}
 				});
        		 if(matrizminas[i][j]==11){
         			c.valor=11;
         			//c.setBackgroundColor(Color.DKGRAY);
         			Drawable d = getResources().getDrawable(drawable.tierra);
                    c.setImageDrawable(d);
         			//c.setEnabled(false);
         		}else{
         			c.valor=matrizminas[i][j];
         			Drawable d = getResources().getDrawable(drawable.tierra);
                    c.setImageDrawable(d);
         			
         			
         		}
        		 
        		c.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                    	if(((Celda) v).valor==0){
             				//c.setBackgroundColor(Color.RED);
             				Drawable d = getResources().getDrawable(drawable.vacia);
                            c.setImageDrawable(d);
             			}
             			if(((Celda) v).valor==1){
             				//c.setBackgroundColor(Color.RED);
             				Drawable d = getResources().getDrawable(drawable.uno);
                            c.setImageDrawable(d);
             			}
             			if(((Celda) v).valor==2){
             				//c.setBackgroundColor(Color.RED);
             				Drawable d = getResources().getDrawable(drawable.dos);
                            c.setImageDrawable(d);
             			}
             			if(((Celda) v).valor==3){
             				//c.setBackgroundColor(Color.RED);
             				Drawable d = getResources().getDrawable(drawable.tres);
                            c.setImageDrawable(d);
             			}
             			if(((Celda) v).valor==4){
             				//c.setBackgroundColor(Color.RED);
             				Drawable d = getResources().getDrawable(drawable.cuatro);
                            c.setImageDrawable(d);
             			}
             			if(((Celda) v).valor==5){
             				//c.setBackgroundColor(Color.RED);
             				Drawable d = getResources().getDrawable(drawable.cinco);
                            c.setImageDrawable(d);
             			}
             			if(((Celda) v).valor==6){
             				//c.setBackgroundColor(Color.RED);
             				Drawable d = getResources().getDrawable(drawable.seis);
                            c.setImageDrawable(d);
             			}
             			if(((Celda) v).valor==7){
             				//c.setBackgroundColor(Color.RED);
             				Drawable d = getResources().getDrawable(drawable.siete);
                            c.setImageDrawable(d);
             			}
             			if(((Celda) v).valor==8){
             				//c.setBackgroundColor(Color.RED);
             				Drawable d = getResources().getDrawable(drawable.ocho);
                            c.setImageDrawable(d);
             			}
             			if(((Celda) v).valor==11){
             				//c.setBackgroundColor(Color.RED);
             				Drawable d = getResources().getDrawable(drawable.bomba);
                            c.setImageDrawable(d);
             			}
                    	
                    	//Drawable d = getResources().getDrawable(drawable.tierra);
                    	//((Celda) v).setImageDrawable(d);
                        
                   	// c.descubrirAdyacentes(c, matriz, d, d);
                    	
                  
                    }
                });
        		
        		matriz[i][j] = c;
        		
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