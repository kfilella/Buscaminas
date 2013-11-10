package com.example.buscaminas;

import com.example.buscaminas.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Buscaminas extends Activity implements OnClickListener {
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscaminas);
        
        View clickExit = findViewById(R.id.exitB);
        clickExit.setOnClickListener(this);
        View clickCredits = findViewById(R.id.creditsB);
        clickCredits.setOnClickListener(this);
        View clickDiff = findViewById(R.id.dificultad);
        clickDiff.setOnClickListener(this);
        View clickHighSc = findViewById(R.id.highscoresB);
        clickHighSc.setOnClickListener(this);
        View clickNewGame = findViewById(R.id.newgameB);
        clickNewGame.setOnClickListener(this);
        
    }

	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.buscaminas, menu);
        return true;
    }
  
    
    
    //--------END TEST BUTTON DIALOGS--------
    
	@Override
	public void onClick(View view) {
		AlertDialog.Builder popupBuilder = new AlertDialog.Builder(this);
		TextView myMsg = new TextView(this);
		myMsg.setText("Central");
		myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
		popupBuilder.setView(myMsg);
		if(view.getId()==findViewById(R.id.creditsB).getId())
    	{   	
			Intent j= new Intent(this,Creditos.class);
	    	 startActivity(j);
    	}
    	
    	if(view.getId()==findViewById(R.id.dificultad).getId())
    	{   	
    	 Intent i= new Intent(this,Juego.class);
    	 startActivity(i);
    	}
    	
    	if(view.getId()==findViewById(R.id.newgameB).getId())
    	{   	
    	AlertDialog dialog = new AlertDialog.Builder(Buscaminas.this).create();
    	dialog.setTitle("New Game");
    	dialog.setMessage("Falta implementar =:D");
    	dialog.show();
    	}
    	
    	if(view.getId()==findViewById(R.id.highscoresB).getId())
    	{   	
    	AlertDialog dialog = new AlertDialog.Builder(Buscaminas.this).create();
    	dialog.setTitle("High Scores");
    	dialog.setMessage("Falta implementar =:D");
    	dialog.show();
    	}
	}
}
