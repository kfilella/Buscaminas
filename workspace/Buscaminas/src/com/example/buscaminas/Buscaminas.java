package com.example.buscaminas;

import com.example.buscaminas.R;

import android.app.Activity;
import android.app.AlertDialog;
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
        View clickDiff = findViewById(R.id.difficultyB);
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

    public void creditsDialog(View view) {
    	AlertDialog dialog = new AlertDialog.Builder(Buscaminas.this).create();
    	dialog.setTitle("Credits");
    	dialog.setMessage("Edinson Sanchez\nAdrian Aguilar\nKevin Filella");
    	dialog.show();
    }
    
    //--------TEST BUTTON DIALOGS--------
    
    public void newGameDialog(View view) {
    	AlertDialog dialog = new AlertDialog.Builder(Buscaminas.this).create();
    	dialog.setTitle("Test - New Game");
    	dialog.setMessage("Under construction");
    	dialog.show();
    }
    
    public void diffDialog(View view) {
    	AlertDialog dialog = new AlertDialog.Builder(Buscaminas.this).create();
    	dialog.setTitle("Test - Difficulty");
    	dialog.setMessage("Under construction");
    	dialog.show();
    }
    
    public void highScDialog(View view) {
    	AlertDialog dialog = new AlertDialog.Builder(Buscaminas.this).create();
    	dialog.setTitle("Test - High Scores");
    	dialog.setMessage("Under construction");
    	dialog.show();
    }
    
    //--------END TEST BUTTON DIALOGS--------
    
	@Override
	public void onClick(View view) {
		AlertDialog.Builder popupBuilder = new AlertDialog.Builder(this);
		TextView myMsg = new TextView(this);
		myMsg.setText("Central");
		myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
		popupBuilder.setView(myMsg);
		if (view.getId()==R.id.exitB)
			finish();
		if (view.getId()==R.id.creditsB)
			creditsDialog(view);
		//--------TEST BUTTONS--------
		if (view.getId()==R.id.newgameB)
			newGameDialog(view);
		if (view.getId()==R.id.highscoresB)
			highScDialog(view);
		if (view.getId()==R.id.difficultyB)
			diffDialog(view);
		//--------END TEST BUTTONS--------
	}
}
