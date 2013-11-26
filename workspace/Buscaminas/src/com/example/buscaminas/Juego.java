package com.example.buscaminas;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.example.buscaminas.R;
import com.example.buscaminas.R.drawable;

import android.R.integer;
import android.R.layout;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
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


@SuppressLint("NewApi")
public class Juego extends Activity {
	private TextView tiempo;
	private TextView puntaje,mina;
	GridLayout tablero;
	private Celda n1;
	Buscaminas b=new Buscaminas();
	LinearLayout layouttiempo;
	ImageButton bandera;
	Chronometer tiempo1;
	int ancho=0,alto=0;
	int minas=0;
	Celda matriz[][];
	
	@SuppressLint("NewApi")
	private final class MyTouchListener implements OnTouchListener {
		  public boolean onTouch(View view, MotionEvent motionEvent) {
		    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
		      ClipData data = ClipData.newPlainText("", "");
		      DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
		      view.startDrag(data, shadowBuilder, view, 0);
		     // view.setVisibility(View.INVISIBLE);
		      return true;
		    } else {
		    return false;
		    }
		  }
		} 
	
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);
        Bundle datos = this.getIntent().getExtras();
        ancho=datos.getInt("dif");
        alto=datos.getInt("dif1");
        minas=datos.getInt("minas");
        layouttiempo=(LinearLayout)this.findViewById(R.id.layouttiempo);
        bandera=(ImageButton)this.findViewById(R.id.bandera);
        bandera.setOnTouchListener(new MyTouchListener());
        bandera.setOnDragListener(new MyDragListener());
        tiempo1 = new Chronometer(this);
        tiempo1.start();
        showElapsedTime();
        layouttiempo.addView(tiempo1);
        layouttiempo.setWeightSum((float) 1.0);
        tiempo=(TextView)this.findViewById(R.id.tiempo);
        tiempo.setText("       Tiempo:     ");
        tiempo.setTextColor(Color.GREEN);
        mina= (TextView)this.findViewById(R.id.minasss);
        mina.setText("        "+Integer.toString(minas));
        
        
        puntaje=(TextView)this.findViewById(R.id.puntaje);
        puntaje.setText("Minas:");
        puntaje.setTextColor(Color.GREEN);
       
        
        tablero=(GridLayout)this.findViewById(R.id.tabla);
        matriz = new Celda[ancho][alto];
        final int matrizminas[][] = new int[ancho][alto];
       
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
        
        for (int i = 0; i <ancho; i++) {
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
        
        
        tablero.setRowCount(alto);
        tablero.setColumnCount(ancho);
        for (int i = 0; i < ancho; i++) {
        	//TableRow fila = new TableRow(this);
        	for (int j=0; j<alto; j++){
        		final Celda c =  new Celda(this,i,j,0);	
        		 c.setOnTouchListener(new OnTouchListener() {
 					
 					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
 						Drawable d1 = getResources().getDrawable(drawable.carasorprendida);
 						Drawable d2 = getResources().getDrawable(drawable.caramuerta);
						ImageButton cara = (ImageButton) findViewById(R.id.imagecara);
 						if(arg1.getAction() == MotionEvent.ACTION_DOWN){
 							if (c.valor==11){
 								cara.setImageDrawable(d2);
 								 for (int i = 0; i < ancho; i++) {
 						        	for (int j=0; j<alto; j++){
 						        		if(matrizminas[i][j]==11 && matriz[i][j].open==false){
 						        			Drawable d = getResources().getDrawable(drawable.bomba);
 				                            matriz[i][j].setImageDrawable(d);
 						        		}
 						        	}
 								 }
 						        	
 							}else{
 								cara.setImageDrawable(d1);
 							}
 							
 							
 							//setPressed(true);
 						}else 
 							if(arg1.getAction() == MotionEvent.ACTION_UP)
 								if (c.valor==11){
 	 								//cara.setImageDrawable(d2);
 	 							}else{
 	 								cara.setImageDrawable(getResources().getDrawable(drawable.download));
 	 							}
 							
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
                            c.open=true;
                            
             			}
             			if(((Celda) v).valor==1){
             				//c.setBackgroundColor(Color.RED);
             				Drawable d = getResources().getDrawable(drawable.uno);
                            c.setImageDrawable(d);
                            c.open=true;
                            //c.setPressed(true);
             			}
             			if(((Celda) v).valor==2){
             				//c.setBackgroundColor(Color.RED);
             				Drawable d = getResources().getDrawable(drawable.dos);
                            c.setImageDrawable(d);
                            c.open=true;
             			}
             			if(((Celda) v).valor==3){
             				//c.setBackgroundColor(Color.RED);
             				Drawable d = getResources().getDrawable(drawable.tres);
                            c.setImageDrawable(d);
                            c.open=true;
             			}
             			if(((Celda) v).valor==4){
             				//c.setBackgroundColor(Color.RED);
             				Drawable d = getResources().getDrawable(drawable.cuatro);
                            c.setImageDrawable(d);
                            c.open=true;
             			}
             			if(((Celda) v).valor==5){
             				//c.setBackgroundColor(Color.RED);
             				Drawable d = getResources().getDrawable(drawable.cinco);
                            c.setImageDrawable(d);
                            c.open=true;
             			}
             			if(((Celda) v).valor==6){
             				//c.setBackgroundColor(Color.RED);
             				Drawable d = getResources().getDrawable(drawable.seis);
                            c.setImageDrawable(d);
                            c.open=true;
             			}
             			if(((Celda) v).valor==7){
             				//c.setBackgroundColor(Color.RED);
             				Drawable d = getResources().getDrawable(drawable.siete);
                            c.setImageDrawable(d);
                            c.open=true;
             			}
             			if(((Celda) v).valor==8){
             				//c.setBackgroundColor(Color.RED);
             				Drawable d = getResources().getDrawable(drawable.ocho);
                            c.setImageDrawable(d);
                            c.open=true;
             			}
             			if(((Celda) v).valor==11){
             				//c.setBackgroundColor(Color.RED);
             				Drawable d = getResources().getDrawable(drawable.bomba);
                            c.setImageDrawable(d);
                            c.open=true;
             			}
                    	
                    	//Drawable d = getResources().getDrawable(drawable.tierra);
                    	//((Celda) v).setImageDrawable(d);
                        
                   	c.descubrirAdyacentes(ancho-1,alto-1,matriz);
                    	
                  
                    }
                });
        		
        		matriz[i][j] = c;
        		tablero.addView((View)matriz[i][j]);
        	}
    	}
        
        
        
        
        

    }
    
    class MyDragListener implements OnDragListener {
    	  Drawable enterShape = getResources().getDrawable(R.drawable.bomba);
    	 // Drawable normalShape = getResources().getDrawable(R.drawable.shape);
    	  
    	 

		@Override
		public boolean onDrag(View v, DragEvent event) {
			int action = event.getAction();
    	    switch (event.getAction()) {
    	    case DragEvent.ACTION_DRAG_STARTED:
    	    // do nothing
    	      break;
    	    case DragEvent.ACTION_DRAG_ENTERED:
    	      //((ImageButton) v).setImageDrawable(enterShape);
    	      break;
    	    case DragEvent.ACTION_DRAG_EXITED:        
    	    	((ImageButton) v).setImageDrawable(enterShape);
    	      break;
    	    case DragEvent.ACTION_DROP:
    	      // Dropped, reassign View to ViewGroup
    	     /* View view = (View) event.getLocalState();
    	      ViewGroup owner = (ViewGroup) view.getParent();
    	      owner.removeView(view);
    	      LinearLayout container = (LinearLayout) v;
    	      container.addView(view);
    	      view.setVisibility(View.VISIBLE);*/
    	      break;
    	    case DragEvent.ACTION_DRAG_ENDED:
    	    	event.getX();
    	    	event.getY();
    	    	for (int i = 0; i < ancho; i++) {
    	          	for (int j=0; j<alto; j++){
    	          		matriz[i][j].getX() ;
    	          		matriz[i][j].getY();
    	          		
    	          	}
    	          }
    	    	((ImageButton) v).setImageDrawable(enterShape);
    	      default:
    	      break;
    	    }
    	    return true;
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