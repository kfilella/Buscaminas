package com.example.buscaminas;

import com.example.buscaminas.R;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.widget.TextView;


public class Creditos extends Activity {
	TextView txtcredito;
	TextView n3;
	TextView n2;
	TextView n1;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creditos);
        txtcredito=(TextView)this.findViewById(R.id.txtcredito);
        n3=(TextView)this.findViewById(R.id.n3);
        n2=(TextView)this.findViewById(R.id.n2);
        n1=(TextView)this.findViewById(R.id.n1);
        txtcredito.setTextColor(Color.BLUE);
        n1.setTextColor(Color.BLUE);
        n2.setTextColor(Color.BLUE);
        n3.setTextColor(Color.BLUE);
        
    }
    
}