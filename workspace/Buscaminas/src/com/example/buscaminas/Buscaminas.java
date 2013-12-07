package com.example.buscaminas;

import com.example.buscaminas.R;

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Buscaminas extends Activity implements OnClickListener {
	int dificultad= 5;
	Juego j;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscaminas);
        
        View clickExit = findViewById(R.id.exitB);
        clickExit.setOnClickListener(this);
        View clickCredits = findViewById(R.id.creditsB);
        clickCredits.setOnClickListener(this);
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
    	
    	    	
    	
    	if(view.getId()==findViewById(R.id.newgameB).getId())
    	{   	
    		startActivity(new Intent(this, Dificultad.class));
    		
    	}
    	
    	if(view.getId()==findViewById(R.id.highscoresB).getId())
    	{   	
    		startActivity(new Intent(this, scores.class));
    	}
    	if(view.getId()==findViewById(R.id.exitB).getId())
    	{
    		finish();
    	}
	}
}
