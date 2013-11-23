package com.example.buscaminas;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ToggleButton;

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
                        if(celda.valor!=11){
                                if(celda.valor==0){
                                //        matriz[celda.pos_x][celda.pos_y].setPressed(true);
                                        if (celda.pos_x-1>=0&&celda.pos_y-1>=0 )
                                        {
                                                //Celda c = matriz[celda.pos_x-1][celda.pos_y-1];
                                                if (matriz[celda.pos_x-1][celda.pos_y-1].isPressed()==false){
                                                matriz[celda.pos_x-1][celda.pos_y-1].setPressed(true);
                                                descubrirAdyacentes(matriz[celda.pos_x-1][celda.pos_y-1], matriz, ancho, alto);
                                                }
                                        }
                                        
                                        if (celda.pos_x-1>=0){
                                                if(matriz[celda.pos_x-1][celda.pos_y].isPressed()==false){
                                                matriz[celda.pos_x-1][celda.pos_y].setPressed(true);
                                                descubrirAdyacentes(matriz[celda.pos_x-1][celda.pos_y], matriz, ancho, alto);
                                                
                                                }
                                        }
                                        
                                        if (celda.pos_y-1>=0){
                                                if(matriz[celda.pos_x][celda.pos_y-1].isPressed()==false){
                                                matriz[celda.pos_x][celda.pos_y-1].setPressed(true);
                                                descubrirAdyacentes(matriz[celda.pos_x][celda.pos_y-1], matriz, ancho, alto);
                                                
                                                }
                                        }
                                        
                                        
                                        if (celda.pos_x+1<=ancho-1)
                                                if(matriz[celda.pos_x+1][celda.pos_y].isPressed()==false){
                                                        matriz[celda.pos_x+1][celda.pos_y].setPressed(true);
                                                        descubrirAdyacentes(matriz[celda.pos_x+1][celda.pos_y], matriz, ancho, alto);
                                                        
                                                        }
                                        /*
                                        if (celda.pos_y+1<=alto-1)
                                                if(matriz[celda.pos_x][celda.pos_y+1].isPressed()==false){
                                                        matriz[celda.pos_x][celda.pos_y+1].setPressed(true);
                                                        descubrirAdyacentes(matriz[celda.pos_x][celda.pos_y+1], matriz, ancho, alto);
                                                        
                                                        }
                                        */
                                        /*
                                        if (celda.pos_x+1<=ancho-1&& celda.pos_y+1<=alto-1)
                                                if(matriz[celda.pos_x+1][celda.pos_y+1].isPressed()==false){
                                                matriz[celda.pos_x+1][celda.pos_y+1].setPressed(true);
                                                descubrirAdyacentes(matriz[celda.pos_x+1][celda.pos_y+1], matriz, ancho, alto);
                                                
                                                }*/
                                        if (celda.pos_x+1<=ancho-1&&celda.pos_y-1>=0)
                                                if(matriz[celda.pos_x+1][celda.pos_y-1].isPressed()==false){
                                                        matriz[celda.pos_x+1][celda.pos_y-1].setPressed(true);
                                                        descubrirAdyacentes(matriz[celda.pos_x+1][celda.pos_y-1], matriz, ancho, alto);
                                                        
                                                        }/*
                                        if (celda.pos_x-1>=0&&celda.pos_y+1<=alto-1)
                                                if(matriz[celda.pos_x-1][celda.pos_y+1].isPressed()==false){
                                                        matriz[celda.pos_x-1][celda.pos_y+1].setPressed(true);
                                                        descubrirAdyacentes(matriz[celda.pos_x-1][celda.pos_y+1], matriz, ancho, alto);
                                                        
                                                        }                */
                                                                        
                                }
                        }        
                }
        }
}
