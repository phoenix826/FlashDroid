package com.usability.flashdroid;

import com.usability.flashdroid.data_source.CardDataSource;
import com.usability.flashdroid.data_source.DeckDataSource;
import com.usability.flashdroid.model.Card;
import com.usability.flashdroid.model.Deck;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditCardActivity extends Activity {
	
	private CardDataSource cardSource;
	private DeckDataSource deckSource;
	private Card currentCard;
	private Deck currentDeck;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);
        
        this.cardSource = new CardDataSource(this);
        this.deckSource = new DeckDataSource(this);
        
        Bundle extras = getIntent().getExtras();
        long cardId = extras.getLong("cardId");
        long deckId = extras.getLong("deckId");
        
        cardSource.open();
        this.currentCard = cardSource.getCardById(cardId);
        cardSource.close();
        
        deckSource.open();
        this.currentDeck = deckSource.getDeckById(deckId);
        deckSource.close();
        
        EditText termText = (EditText) findViewById(R.id.cardEditTerm);
        termText.setText(this.currentCard.getTerm());
        
        EditText definitionText = (EditText) findViewById(R.id.cardEditDefinition);
        definitionText.setText(this.currentCard.getDefinition());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_edit_card, menu);
        return true;
    }
    
    public void saveCard(View view) {    	
		EditText cardTermText = (EditText) findViewById(R.id.cardEditTerm);
		String cardTerm = cardTermText.getText().toString();
		
		EditText cardDefinitionText = (EditText) findViewById(R.id.cardEditDefinition);
		String cardDefinition = cardDefinitionText.getText().toString();
		
    	cardSource.open();
    	cardSource.updateCard(currentCard.getId(), cardTerm, cardDefinition);
    	cardSource.close();
    	
		Context context = getApplicationContext();
		CharSequence text = "Successfully updated card";
		int duration = Toast.LENGTH_LONG;
		
		Intent newIntent = new Intent(this, ManageCardsActivity.class);
		newIntent.putExtra("deckId", currentDeck.getId());
		this.startActivity(newIntent);
		
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
    }
}
