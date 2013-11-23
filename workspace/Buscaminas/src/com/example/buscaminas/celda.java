package com.example.buscaminas;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageButton;

public class Celda extends ImageButton {
	int pos_x;
	int pos_y;
	int valor;
	public Celda(Context context, int posx, int posy, int val) {
		super(context);
		this.pos_x = posx;
		this.pos_y = posy;
		this.valor = 0;
	}
	public void descubrirAdyacentes(Celda celda, Celda[][] matriz, int ancho, int alto){
		if(celda.isPressed()){
			if(celda.valor!=-1){
				if(celda.valor==0){
					celda.setPressed(true);
					if (matriz[celda.pos_x-1][celda.pos_y-1].valor==0)
						descubrirAdyacentes(matriz[celda.pos_x-1][celda.pos_y-1], matriz, ancho, alto);
					if (matriz[celda.pos_x-1][celda.pos_y].valor==0)
						descubrirAdyacentes(matriz[celda.pos_x-1][celda.pos_y], matriz, ancho, alto);
					if (matriz[celda.pos_x][celda.pos_y-1].valor==0)
						descubrirAdyacentes(matriz[celda.pos_x][celda.pos_y-1], matriz, ancho, alto);
					if (matriz[celda.pos_x+1][celda.pos_y].valor==0)
						descubrirAdyacentes(matriz[celda.pos_x+1][celda.pos_y], matriz, ancho, alto);
					if (matriz[celda.pos_x][celda.pos_y+1].valor==0)
						descubrirAdyacentes(matriz[celda.pos_x][celda.pos_y+1], matriz, ancho, alto);
					if (matriz[celda.pos_x+1][celda.pos_y+1].valor==0)
						descubrirAdyacentes(matriz[celda.pos_x+1][celda.pos_y+1], matriz, ancho, alto);
					if (matriz[celda.pos_x+1][celda.pos_y-1].valor==0)
						descubrirAdyacentes(matriz[celda.pos_x+1][celda.pos_y-1], matriz, ancho, alto);
					if (matriz[celda.pos_x-1][celda.pos_y+1].valor==0)
						descubrirAdyacentes(matriz[celda.pos_x-1][celda.pos_y+1], matriz, ancho, alto);
				}
			}	
		}
	}
}
