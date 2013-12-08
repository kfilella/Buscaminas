package com.example.buscaminas;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class scores extends Activity {
	
	TextView prueb;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores);
        prueb=(TextView)findViewById(R.id.prueb);
        onClickCargar();
        
        
        
        
    }
	
	public void onClickCargar(){
		
		try {
            InputStreamReader archivo = new InputStreamReader(
                    openFileInput("textFile.txt"));
            BufferedReader br = new BufferedReader(archivo);
            String linea = br.readLine();
            String todo= "";
            while (linea != null) {
            	todo = todo + linea + "\n";
                linea = br.readLine();
                
            	
            }
            
            br.close();
            archivo.close();
            prueb.setText(todo);
            Toast.makeText(getBaseContext(), "Cargado", Toast.LENGTH_SHORT).show();
           
        } catch (IOException e) {
        }
		
	}
	
}
