package com.example.buscaminas;

import com.example.buscaminas.R.drawable;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ToggleButton;

public class Celda extends ImageButton {
        int posx;
        int posy;
        int valor;
        boolean open;
        public Celda(Context context, int posx, int posy, int val) {
                super(context);
                this.posx = posx;
                this.posy = posy;
                this.valor = 0;
                boolean open = false;
        }/*
        public void descubrirAdyacentes(Celda[][] celdas,int ancho,int alto){
    		if(this.valor>0 && this.valor<11){ //si celda tiene numero asignado 1-8 descubre numero
    			if(this.valor==1)
    				this.setImageDrawable(getResources().getDrawable(drawable.uno));
    			if(this.valor==2)
    				this.setImageDrawable(getResources().getDrawable(drawable.dos));
    			if(this.valor==3)
    				this.setImageDrawable(getResources().getDrawable(drawable.tres));
    			if(this.valor==4)
    				this.setImageDrawable(getResources().getDrawable(drawable.cuatro));
    			if(this.valor==5)
    				this.setImageDrawable(getResources().getDrawable(drawable.cinco));
    			if(this.valor==6)
    				this.setImageDrawable(getResources().getDrawable(drawable.seis));
    			if(this.valor==7)
    				this.setImageDrawable(getResources().getDrawable(drawable.siete));
    			if(this.valor==8)
    				this.setImageDrawable(getResources().getDrawable(drawable.ocho));
    		}
        	if(valor==0){
        		this.setImageDrawable(getResources().getDrawable(drawable.vacia));
        		if(this.posx>=1 && (this.posy+1)<alto && this.posy>=1){
        			celdas[this.posx-1][this.posy-1].descubrirAdyacentes(celdas, ancho, alto);
        			celdas[this.posx-1][this.posy].descubrirAdyacentes(celdas, ancho, alto);
        			celdas[this.posx-1][this.posy+1].descubrirAdyacentes(celdas, ancho, alto);
        		}
        		if(this.posy>=1  && (this.posx+1)<ancho){
        			celdas[this.posx][this.posy-1].descubrirAdyacentes(celdas, ancho, alto);
        			celdas[this.posx+1][this.posy-1].descubrirAdyacentes(celdas, ancho, alto);
        		}
        		if((this.posx+1)<ancho && (this.posy+1)<alto){
        			celdas[this.posx+1][this.posy].descubrirAdyacentes(celdas, ancho, alto);
        			celdas[this.posx+1][this.posy+1].descubrirAdyacentes(celdas, ancho, alto);
        		}
        		if((this.posy+1)<alto  && (this.posx+1)<ancho ){
        			celdas[this.posx][this.posy+1].descubrirAdyacentes(celdas, ancho, alto);
        		}
        	}
        }*/
        public void descubrirAdyacentes(int ancho, int alto, Celda[][] celdas){
        	//if(this.posx>=0 && this.posy>=0 && this.posx<=ancho && this.posy<=alto){
	    		if(this.valor>0 && this.valor<11){ //si celda tiene numero asignado 1-8 descubre numero
	    			if(this.valor==1)
	    				this.setImageDrawable(getResources().getDrawable(drawable.uno));
	    			if(this.valor==2)
	    				this.setImageDrawable(getResources().getDrawable(drawable.dos));
	    			if(this.valor==3)
	    				this.setImageDrawable(getResources().getDrawable(drawable.tres));
	    			if(this.valor==4)
	    				this.setImageDrawable(getResources().getDrawable(drawable.cuatro));
	    			if(this.valor==5)
	    				this.setImageDrawable(getResources().getDrawable(drawable.cinco));
	    			if(this.valor==6)
	    				this.setImageDrawable(getResources().getDrawable(drawable.seis));
	    			if(this.valor==7)
	    				this.setImageDrawable(getResources().getDrawable(drawable.siete));
	    			if(this.valor==8)
	    				this.setImageDrawable(getResources().getDrawable(drawable.ocho));
	    		}
	    			
	    		if(this.valor==0 && this.open == false){ //si celda esta vacia
		    			this.setImageDrawable(getResources().getDrawable(drawable.vacia)); //descubre celda vacia
		    			this.open = true;
		    			//if(this.posx+1<=ancho && this.posy+1<=alto)
		    			//	celdas[this.posx+1][this.posy+1].descubrirAdyacentes(ancho,alto,celdas);
		    			if(this.posx+1<=ancho)
		    				celdas[this.posx+1][this.posy].descubrirAdyacentes(ancho,alto,celdas);
		    			if(this.posy+1<=alto)
		    				celdas[this.posx][this.posy+1].descubrirAdyacentes(ancho,alto,celdas);
		    			//if(this.posx-1>=0 && this.posy-1>=0)
		    			//	celdas[this.posx-1][this.posy-1].descubrirAdyacentes(ancho,alto,celdas);
		    			if(this.posx-1>=0)
		        			celdas[this.posx-1][this.posy].descubrirAdyacentes(ancho,alto,celdas);        				
		    			if(this.posy-1>=0)    				
		    			 	celdas[this.posx][this.posy-1].descubrirAdyacentes(ancho,alto,celdas);
		    			//if(this.posx-1>=0 && this.posy+1<=alto)
		    			//	celdas[this.posx-1][this.posy+1].descubrirAdyacentes(ancho,alto,celdas);
		    			//if(this.posx+1<=ancho && this.posy-1>=0)
		    			//	celdas[this.posx+1][this.posy-1].descubrirAdyacentes(ancho,alto,celdas);
		    		
	    		}
        	}
        
        
}


