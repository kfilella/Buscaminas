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
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
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
public class Juego extends Activity implements OnClickListener{
	private TextView tiempo;
	private TextView puntaje,mina;
	GridLayout tablero;
	private Celda n1;
	Buscaminas b=new Buscaminas();
	LinearLayout layouttiempo;
	ImageButton bandera;
	ImageButton cara ;
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
        ancho=datos.getInt("dif1");
        alto=datos.getInt("dif");
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
        cara = (ImageButton) findViewById(R.id.imagecara);
        cara.setOnClickListener(this);
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
        for(int l=1; l<=minas; l++ ){
        int i=r.nextInt(ancho);
        int j=r.nextInt(alto);
        if(matrizminas[i][j]!=11)     matrizminas[i][j]=11;
        else
        	l--;
        }
        
        for (int i = 0; i <ancho; i++) {
            for (int j=0; j<alto; j++){
             int cont=0;
             
             if(matrizminas[i][j]!=11){
           if(i-1>=0 && j-1>=0){
               if(matrizminas[i-1][j-1]==11)
                 cont++;
           }
           if(i-1>=0){
               if(matrizminas[i-1][j]==11)
               cont++;
           }
           if(i-1>=0 && j+1<alto){
               if(matrizminas[i-1][j+1]==11)
               cont++;
              }
           if(i+1<ancho && j-1>=0){
               if(matrizminas[i+1][j-1]==11)
                 cont++;
           }
           if(i+1<ancho && j+1<alto){
               if(matrizminas[i+1][j+1]==11)
               cont++;
           }
           if(j+1<alto){
               if(matrizminas[i][j+1]==11)
               cont++;
              }
           if(i+1<ancho){
               if(matrizminas[i+1][j]==11)
                 cont++;
           }
           if(j-1>=0){
               if(matrizminas[i][j-1]==11)
               cont++;
           }
           matrizminas[i][j]=cont;
             }
               
            }
             
           }
        
        tablero.setRowCount(ancho);
        tablero.setColumnCount(alto);
        for (int i = 0; i < ancho; i++) {
        	for (int j=0; j<alto; j++){
        		final Celda c =  new Celda(this,i,j,0);	
        		 c.setOnTouchListener(new OnTouchListener() {
 					
 					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
 						Drawable d1 = getResources().getDrawable(drawable.carasorprendida);
 						Drawable d2 = getResources().getDrawable(drawable.caramuerta);
						
 						if(arg1.getAction() == MotionEvent.ACTION_DOWN){
 							if (c.valor==11){
 								cara.setImageDrawable(d2);
 								 for (int i = 0; i < ancho; i++) {
 						        	for (int j=0; j<alto; j++){
 						        		matriz[i][j].setEnabled(false);
 						        		if(matrizminas[i][j]==11 && matriz[i][j].open==false){
 						        			Drawable d = getResources().getDrawable(drawable.bomba);
 				                            matriz[i][j].setImageDrawable(d);
 						        		}
 						        	}
 						        	
 								 }
 								AlertDialog dialog = new AlertDialog.Builder(Juego.this).create();
 								TextView myMsg = new TextView(Juego.this);
 								myMsg.setText("Has perdido");
 								myMsg.setGravity(Gravity.CENTER);
 								dialog.setView(myMsg);
 								dialog.show();
 						        	
 							}else{
 								cara.setImageDrawable(d1);
 							}
 							
 							
 							//setPressed(true);
 						}else 
 							if(arg1.getAction()==MotionEvent.ACTION_MOVE){
 								cara.setImageDrawable(getResources().getDrawable(drawable.download));
 							}
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
         			Drawable d = getResources().getDrawable(drawable.tierra);
                    c.setImageDrawable(d);
                    
         			//c.setEnabled(false);
         		}else{
         			c.valor=matrizminas[i][j];
         			Drawable d = getResources().getDrawable(drawable.tierra);
                    c.setImageDrawable(d);
         			
         			
         		}
        		c.setOnLongClickListener(new OnLongClickListener() { 
        	        @Override
        	        public boolean onLongClick(View v) {
        	        	if(((Celda) v).bandera==false && ((Celda) v).open==false){
	        	        	c.setImageDrawable(getResources().getDrawable(drawable.band));
	        	        	c.bandera=true;
	        	            return true;
        	        	}
        	        	if(((Celda) v).bandera==true){
	        	        	c.setImageDrawable(getResources().getDrawable(drawable.tierra));
	        	        	c.bandera=false;
	        	            return true;
        	        	}
        	        	return true;
        	        }
        	    });
        		c.setOnClickListener(new View.OnClickListener() {
        			
                    public void onClick(View v) {
                    	if(((Celda) v).bandera==false){
	                    	if(((Celda) v).valor==0){
	             				//c.setBackgroundColor(Color.RED);
	             				Drawable d = getResources().getDrawable(drawable.vacia);
	                            c.setImageDrawable(d);
	                            
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
	                    	c.descubrirAdyacentes(ancho-1,alto-1,matriz);
	                    	int contad = 0;
	                    	int num=0;
	            			for (int i = 0; i < ancho; i++) {
	            	        	for (int j=0; j<alto; j++){
	            	        		if(matriz[i][j].valor < 11){
	            	        		num++;
	            					if(matriz[i][j].open== true){
	            						contad++;
	            					}
	            	        	  }
	            				}
	            			}
	            			if(contad==num){
	            				AlertDialog dialog = new AlertDialog.Builder(Juego.this).create();
									TextView myMsg = new TextView(Juego.this);
									myMsg.setText("Felicitaciones! Has ganado");
									myMsg.setGravity(Gravity.CENTER);
									dialog.setView(myMsg);
									dialog.show();
									Drawable d = getResources().getDrawable(drawable.caragafas);
		                            cara.setImageDrawable(d);
	            			}else{
	            				contad=0;
	            				num=0;
	            			}
	                    	
	                  
	                    }
                    }
	                });
	        		
	        		matriz[i][j] = c;
	        		tablero.addView((View)matriz[i][j]);
	        	}
    	}
        
        
        
        
        

    }
    
    class MyDragListener implements OnDragListener {
    	  Drawable enterShape = getResources().getDrawable(R.drawable.band);
    	 // Drawable normalShape = getResources().getDrawable(R.drawable.shape);
    	  
        @Override
        public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DROP:
                	View view = (View) event.getLocalState();
                	view.setVisibility(View.INVISIBLE);
                	Celda dropTarget = (Celda) v;
                	ImageButton dropped = (ImageButton) view;
                	dropTarget.setImageDrawable(dropped.getDrawable());
                	dropTarget.valor = 99;
                    return true;
                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    break;
                default:
                    break;
            }
        return true;
        }
    	}
    
  
    private void showElapsedTime() {
        long elapsedMillis = SystemClock.elapsedRealtime() - tiempo1.getBase();            
        Toast.makeText(Juego.this, "Elapsed milliseconds: " + elapsedMillis, 
                Toast.LENGTH_SHORT).show();
    }


@Override
public void onClick(View v) {
	if(v.getId()==findViewById(R.id.imagecara).getId()){
		
		Intent intent = getIntent();
        finish();
        startActivity(intent);
	}
	// TODO Auto-generated method stub
	
}
    
}