package com.example.buscaminas;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class scores extends Activity {
	private static final int READ_BLOCK_SIZE = 100;
	TextView prueba;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores);
        prueba = (TextView) findViewById(R.id.prueba);
        onClickCargar();
        
        
        
    }
	
	public void onClickCargar(){
	    try{
	        FileInputStream fis = openFileInput("textFile.txt");
	        InputStreamReader isr = new InputStreamReader(fis);
	         
	        char[] inputBuffer = new char[READ_BLOCK_SIZE];
	        String s = "";
	         
	        int charRead;
	        while((charRead = isr.read(inputBuffer)) > 0){
	            // Convertimos los char a String
	            String readString = String.copyValueOf(inputBuffer, 0, charRead);
	            s += readString;
	             
	            inputBuffer = new char[READ_BLOCK_SIZE];
	        }
	         
	        // Establecemos en el EditText el texto que hemos leido
	        prueba.setText(s);
	         
	        // Mostramos un Toast con el proceso completado
	        Toast.makeText(getBaseContext(), "Cargado", Toast.LENGTH_SHORT).show();
	         
	        isr.close();
	    }catch (IOException ex){
	        ex.printStackTrace();
	    }
	}
	
}
