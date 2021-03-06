package com.example.buscaminas;



import java.util.ArrayList;

import com.example.buscaminas.R.drawable;

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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ScrollView;
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
	int a=9,b=9,c=10;
	
	
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dificultad);
        
        
        facil=(Button)findViewById(R.id.facil);
        facil.setGravity(Gravity.CENTER);
        facil.setOnClickListener( this);
        facil.setBackground(getResources().getDrawable(drawable.tierra));
        medio=(Button)findViewById(R.id.medio);
        medio.setGravity(Gravity.CENTER);
        medio.setOnClickListener(this);
        medio.setBackground(getResources().getDrawable(drawable.tierra));
        dificil=(Button)findViewById(R.id.dificil);
        dificil.setGravity(Gravity.CENTER);
        dificil.setOnClickListener(this);
        dificil.setBackground(getResources().getDrawable(drawable.tierra));
        personalizado=(Button)findViewById(R.id.personalizado);
        personalizado.setGravity(Gravity.CENTER);
        personalizado.setOnClickListener(this);
        personalizado.setBackground(getResources().getDrawable(drawable.tierra));
        
               
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
    		ScrollView scrol=new ScrollView(this);
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
    	    text.setTextColor(Color.BLUE);
     	    text.setPadding(10, 10, 10, 10);
    	    
     	   final NumberPicker seek3=new NumberPicker(this);
      	    seek3.setMaxValue((a*b)/2);
      	    seek3.setMinValue(10);
      	    seek3.setWrapSelectorWheel(true);
      	    seek3.setOnValueChangedListener( new NumberPicker.
      	            OnValueChangeListener() {
      	            @Override
      	            public void onValueChange(NumberPicker picker, int
      	                oldVal, int newVal) {
      	            	c=newVal;
      	            }
      	        });
    	     
    	    NumberPicker seek1=new NumberPicker(this);
    	    seek1.setMaxValue(30);
    	    seek1.setMinValue(9);
    	    //seek1.setValue(9);
    	    seek1.setWrapSelectorWheel(true);
    	    seek1.setOnValueChangedListener( new NumberPicker.
    	            OnValueChangeListener() {
    	            @Override
    	            public void onValueChange(NumberPicker picker, int
    	                oldVal, int newVal) {
       	            	a=newVal;
    	            	if(a*b<100)
    	            		seek3.setMinValue(10);
    	          	    if(a*b>=100 && a*b<200)
    	          	    	seek3.setMinValue(15);
    	          	    if(a*b>=200 && a*b<300)
    	        	    	seek3.setMinValue(35);
    	          	    if(a*b>=300 && a*b<400)
    	          	    	seek3.setMinValue(45);
    	          	    if(a*b>=400 && a*b<500)
    	          	    	seek3.setMinValue(75);
    	          	    if(a*b>=500 && a*b<800)
    	          	    	seek3.setMinValue(150);
    	            	seek3.setMaxValue((a*b)/2);
    	            	seek3.setValue((a*b)/2);
    	            }
    	        });
    	    
    	    NumberPicker seek2=new NumberPicker(this);
    	    seek2.setMaxValue(24);
    	    seek2.setMinValue(9);
    	    //seek2.setValue(9);
    	    seek2.setWrapSelectorWheel(true);
    	    seek2.setOnValueChangedListener( new NumberPicker.
    	            OnValueChangeListener() {
    	            @Override
    	            public void onValueChange(NumberPicker picker, int
    	                oldVal, int newVal) {
    	            	b=newVal;
    	            	if(a*b<100)
    	            		seek3.setMinValue(10);
    	          	    if(a*b>=100 && a*b<200)
    	          	    	seek3.setMinValue(15);
    	          	    if(a*b>=200 && a*b<300)
    	        	    	seek3.setMinValue(35);
    	          	    if(a*b>=300 && a*b<400)
    	          	    	seek3.setMinValue(45);
    	          	    if(a*b>=400 && a*b<500)
    	          	    	seek3.setMinValue(75);
    	          	    if(a*b>=500 && a*b<800)
    	          	    	seek3.setMinValue(150);
    	            	seek3.setMaxValue((a*b)/2);
    	            	seek3.setValue((a*b)/2);
    	            }
    	        });
    	    
    	    
    	    
    	    
    	    an.addView(ancho);
    	    an.addView(seek1);
    	    
    	    an.addView(alto);
    	    an.addView(seek2);
    	    mi.addView(minas);
    	    mi.addView(seek3);
    	    
    	    linear.addView(text);
    	    linear.addView(an);
    	    linear.addView(mi);
    	    scrol.addView(linear);
    	    linear.setBackgroundDrawable(getResources().getDrawable(drawable.drag));
    	    popupBuilder.setView(scrol); 
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


