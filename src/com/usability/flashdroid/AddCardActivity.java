package com.usability.flashdroid;

import com.usability.flashdroid.data_source.CardDataSource;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddCardActivity extends Activity {
	
	private CardDataSource cardSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_card, menu);
        return true;
    }
    
    public void saveCard(View view) {
        cardSource = new CardDataSource(this);
        
        Bundle extras = getIntent().getExtras();
        long deckId = extras.getLong("deckId");
        
		EditText cardTermText = (EditText) findViewById(R.id.cardTerm);
		String cardTerm = cardTermText.getText().toString();
		
		EditText cardDefinitionText = (EditText) findViewById(R.id.cardDefinition);
		String cardDefinition = cardDefinitionText.getText().toString();
        
        cardSource.open();
		cardSource.createCard(deckId, cardTerm, cardDefinition);
		cardSource.close();
		
		Context context = getApplicationContext();
		CharSequence text = "Successfully created card";
		int duration = Toast.LENGTH_LONG;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		
		Intent newIntent = new Intent(AddCardActivity.this, ManageCardsActivity.class);
		newIntent.putExtra("deckId", deckId);
		AddCardActivity.this.startActivity(newIntent);
    }
}
