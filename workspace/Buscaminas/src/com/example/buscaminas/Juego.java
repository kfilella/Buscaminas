package com.example.buscaminas;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.example.buscaminas.R;
import com.example.buscaminas.R.drawable;

import android.R.integer;
import android.R.layout;
import android.R.string;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
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
import android.widget.EditText;
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
	Buscaminas b=new Buscaminas();
	LinearLayout layouttiempo;
	ImageButton bandera;
	ImageButton cara ;
	Chronometer tiempo1;
	TextView tiempofinal;
	CharSequence temfinal;
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
        final Jugador jugador = new Jugador();
        jugador.ancho = ancho;
        jugador.alto = alto;
        jugador.minas = minas;
        
        
        puntaje=(TextView)this.findViewById(R.id.puntaje);
        puntaje.setText("Minas:");
        puntaje.setTextColor(Color.GREEN);
       
        
        tablero=(GridLayout)this.findViewById(R.id.tabla);
        matriz = new Celda[ancho][alto];
        final int matrizminas[][] = new int[ancho][alto];
       
        for (int i = 0; i < ancho; i++){
        	for (int j=0; j<alto; j++){
        		matrizminas[i][j]=0;
        	}
    	}
       
        Random r = new Random();
        for(int l=1; l<=minas; l++ ){
	        int i=r.nextInt(ancho);
	        int j=r.nextInt(alto);
	        if(matrizminas[i][j]!=11)     
	        	matrizminas[i][j]=11;
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
        
        for (int i=0;i<ancho;i++) {
        	for (int j=0;j<alto;j++){
        		final Celda c = new Celda(this,i,j,0);	
        		 if(matrizminas[i][j]==11){
         			c.valor=11;
         			Drawable d = getResources().getDrawable(drawable.tierra);
                    c.setImageDrawable(d);
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
                    		switch(((Celda)v).valor){
	                    		case 0: c.setImageDrawable(getResources().getDrawable(drawable.vacia)); 
	                    		break;
	                    		case 1: {
	                    			c.setImageDrawable(getResources().getDrawable(drawable.uno));
		                            c.open=true;
	                    		}break;
	                    		case 2: {
	                    			c.setImageDrawable(getResources().getDrawable(drawable.dos));
		                            c.open=true;
	                    		}break;
	                    		case 3: {
	                    			c.setImageDrawable(getResources().getDrawable(drawable.tres));
		                            c.open=true;
	                    		}break;
	                    		case 4: {
	                    			c.setImageDrawable(getResources().getDrawable(drawable.cuatro));
		                            c.open=true;
	                    		}break;
	                    		case 5: {
	                    			c.setImageDrawable(getResources().getDrawable(drawable.cinco));
		                            c.open=true;
	                    		}break;
	                    		case 6: {
	                    			c.setImageDrawable(getResources().getDrawable(drawable.seis));
		                            c.open=true;
	                    		}break;
	                    		case 7: {
	                    			c.setImageDrawable(getResources().getDrawable(drawable.siete));
		                            c.open=true;
	                    		}break;
	                    		case 8: {
	                    			c.setImageDrawable(getResources().getDrawable(drawable.ocho));
		                            c.open=true;
	                    		}break;
	                    		case 11: {
		             				cara.setImageDrawable(getResources().getDrawable(drawable.caramuerta));
		                            c.setImageDrawable(getResources().getDrawable(drawable.bomba));
									 for (int i = 0; i < ancho; i++) {
		 						        	for (int j=0; j<alto; j++){
		 						        		matriz[i][j].setEnabled(false);
		 						        		if(matrizminas[i][j]==11 && matriz[i][j].open==false){
		 				                            matriz[i][j].setImageDrawable(getResources().getDrawable(drawable.bomba));
		 						        		}
		 						        	}	
		 								}
		 								AlertDialog dialog = new AlertDialog.Builder(Juego.this).create();
		 								TextView myMsg = new TextView(Juego.this);
		 								myMsg.setText("Has perdido");
		 								tiempo1.stop();
		 								myMsg.setGravity(Gravity.CENTER);
		 								dialog.setView(myMsg);
		 								dialog.show();
		                            c.open=true;
	                    		}break;
	                    		default: break;
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
	            				//AlertDialog dialog = new AlertDialog.Builder(Juego.this).create();
									//TextView myMsg = new TextView(Juego.this);
									//myMsg.setText("Felicitaciones! Has ganado");
									tiempo1.stop();
									temfinal=tiempo1.getText();
									jugador.tiempo = temfinal;
									onClickGuardar(jugador);
									Drawable d = getResources().getDrawable(drawable.caragafas);
		                            cara.setImageDrawable(d);
									 for (int i = 0; i < ancho; i++)
		 						        	for (int j=0; j<alto; j++)
		 						        		matriz[i][j].setEnabled(false);
	            			}else{
	            				contad=0;
	            				num=0;
	            			}
	                    }
                    }
	            });
        		
    	      c.setOnTouchListener(new OnTouchListener() {
    	    	  public boolean onTouch(View arg0, MotionEvent arg1) {
                      Drawable d1 = getResources().getDrawable(drawable.carasorprendida);
                      if(arg1.getAction() == MotionEvent.ACTION_DOWN)
                          cara.setImageDrawable(d1);
                      else {
                          if(arg1.getAction() == MotionEvent.ACTION_UP)
                        	  cara.setImageDrawable(getResources().getDrawable(drawable.download));
                      }
                      return false;      
    	    	  }
              });
    		matriz[i][j] = c;
    		tablero.addView((View)matriz[i][j]);
        	}
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
public void onClickGuardar(final Jugador j){
	final EditText nombre= new EditText(this);
	AlertDialog.Builder popupBuilder = new AlertDialog.Builder(this);
	nombre.setText("");
	nombre.setGravity(Gravity.CENTER_HORIZONTAL);
	TextView myMsg = new TextView(Juego.this);
	TextView my = new TextView(Juego.this);
	my.setText("Ingrese su nombre:  ");
	myMsg.setText("Felicitaciones! Has ganado");
	LinearLayout linear=new LinearLayout(this);
	LinearLayout linear1=new LinearLayout(this);
	linear.setOrientation(1);
	linear.addView(myMsg);
	linear1.addView(my);
	linear1.addView(nombre);
	linear.addView(linear1);
	popupBuilder.setView(linear); 
    popupBuilder.setNegativeButton("Cancelar", null);
	popupBuilder.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {

        @Override
		public void onClick(DialogInterface dialog, int which) {
        	 try{
        	        FileOutputStream f = openFileOutput("textFile.txt", MODE_APPEND);
        	        OutputStreamWriter os = new OutputStreamWriter(f);
        	        os.append("\n");
        	        os.append("Jugador: "+nombre.getText().toString()+" Matriz: "+j.ancho+"x"+j.alto+" Minas: "+j.minas+" Tiempo: "+j.tiempo+"\n");
        	        os.flush();
        	        os.close();
        	        Toast.makeText(getBaseContext(), "Guardado", Toast.LENGTH_SHORT).show();
        	    }catch (IOException ex){
        	        ex.printStackTrace();
        	    }
			
		}
    });
	popupBuilder.show();
	
   
     
}
    
}