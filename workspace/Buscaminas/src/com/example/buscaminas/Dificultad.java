package com.example.buscaminas;



import java.util.ArrayList;

import android.R.layout;
import android.R.string;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;


@SuppressLint("NewApi")
public class Dificultad extends Activity implements OnClickListener  {
	Button facil;
	Button medio;
	Button dificil;
	Button personalizado;
	int fac=8,facminas=10;
	int med=16,medminas=40;
	int ancho=30, alto=16,difminas=99;
	int a=4,b=4,c=0;
	
	
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dificultad);
        
        
        facil=(Button)findViewById(R.id.facil);
        facil.setGravity(Gravity.CENTER);
        facil.setOnClickListener( this);
        facil.setBackgroundColor(Color.RED);
        facil.setTextColor(Color.GRAY);
        medio=(Button)findViewById(R.id.medio);
        medio.setGravity(Gravity.CENTER);
        medio.setOnClickListener(this);
        medio.setBackgroundColor(Color.RED);
        medio.setTextColor(Color.GRAY);
        dificil=(Button)findViewById(R.id.dificil);
        dificil.setGravity(Gravity.CENTER);
        dificil.setOnClickListener(this);
        dificil.setBackgroundColor(Color.RED);
        dificil.setTextColor(Color.GRAY);
        personalizado=(Button)findViewById(R.id.personalizado);
        personalizado.setGravity(Gravity.CENTER);
        personalizado.setOnClickListener(this);
        personalizado.setBackgroundColor(Color.RED);
        personalizado.setTextColor(Color.GRAY);
        
               
	}


	@Override
	public void onClick(View arg0) {
		
		AlertDialog.Builder popupBuilder = new AlertDialog.Builder(this);
		TextView myMsg = new TextView(this);
		myMsg.setText("Personalizado");
		myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
		popupBuilder.setView(myMsg);
		if(arg0.getId()==findViewById(R.id.facil).getId())
    	{   	
			Intent i= new Intent(this,Juego.class);
			i.putExtra("dif", fac);
			i.putExtra("dif1", fac);
			i.putExtra("minas", facminas);
	       	startActivity(i);
    	}
    	
    	if(arg0.getId()==findViewById(R.id.medio).getId())
    	{   	
    		Intent i= new Intent(this,Juego.class);
    		i.putExtra("dif", med);
    		i.putExtra("dif1", med);
    		i.putExtra("minas", medminas);
	       	startActivity(i);
        		
        	
    	}
    	if(arg0.getId()==findViewById(R.id.dificil).getId())
    	{   	
    		Intent i= new Intent(this,Juego.class);
    		i.putExtra("dif",ancho);
    		i.putExtra("dif1",alto);
    		i.putExtra("minas",difminas);
    	
	       	startActivity(i);
        		
        	
    	}
    	if(arg0.getId()==findViewById(R.id.personalizado).getId())
    	{   	
    		LinearLayout linear=new LinearLayout(this);
    		linear.setOrientation(1); 
    		
    		LinearLayout an=new LinearLayout(this);
    		LinearLayout al=new LinearLayout(this);
    		LinearLayout mi=new LinearLayout(this);
    		
    		
    		 
     	    
    	    TextView ancho=new TextView(this); 
    	    ancho.setText("ANCHO         ");
    	    ancho.setTextColor(Color.BLUE); 
    	    TextView alto=new TextView(this);
    	    alto.setText("ALTO          ");
    	    alto.setTextColor(Color.BLUE);
    	    TextView minas=new TextView(this);
    	    minas.setText("MINAS         ");
    	    minas.setTextColor(Color.BLUE);
    	    
    	    TextView text=new TextView(this); 
    	    text.setText("Personalizar"); 
     	    text.setPadding(10, 10, 10, 10);
    	    final TextView numancho=new TextView(this);
    	    numancho.setText("10");
    	    final TextView numalto=new TextView(this); 
    	    numalto.setText("6");
    	    final TextView numminas=new TextView(this); 
    	    numminas.setText("20");
    	  
    	    
    	    SeekBar seek3=new SeekBar(this);
    	    seek3.setMax(200);
    	    seek3.setProgress(2);
    	    seek3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					c=progress;
					numminas.setText(Integer.toString(progress));
					
				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
				}

				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {						
				}
			});
    	    
    	    
    	    SeekBar seek1=new SeekBar(this); 
    	    seek1.setMax(30);
    	    seek1.setProgress(2);
    	    seek1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					a=progress;
					numancho.setText(Integer.toString(progress));
					}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
				}

				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {						
				}
			});
    	    SeekBar seek2=new SeekBar(this);
    	    seek2.setMax(30);
    	    seek2.setProgress(2);
    	    seek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					b=progress;
					numalto.setText(Integer.toString(progress));
					
					
				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
				}

				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {						
				}
			});
    	    
    	    an.addView(ancho);
    	    an.addView(numancho);
    	    al.addView(alto);
    	    al.addView(numalto);
    	    mi.addView(minas);
    	    mi.addView(numminas);
    	    linear.addView(text);
    	    linear.addView(an);
    	    linear.addView(seek1);
    	    linear.addView(al);
    	    linear.addView(seek2);
    	    linear.addView(mi);
    	    linear.addView(seek3);
    	    popupBuilder.setView(linear); 
    	    popupBuilder.setNegativeButton("cancelar", null);
    	    
    		popupBuilder.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {

                @Override
				public void onClick(DialogInterface dialog, int which) {
					Intent i= new Intent(Dificultad.this,Juego.class);
		    		i.putExtra("dif", a);
		    		i.putExtra("dif1", b);
		    		i.putExtra("minas", c);
			       	startActivity(i);
					
				}
            });
    		popupBuilder.show();
        		
        	
    	}
    	
    	
    	
    	
    	
    	
    	
    	
		
	}


	
	
		
	}


