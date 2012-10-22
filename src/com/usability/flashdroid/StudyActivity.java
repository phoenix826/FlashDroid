package com.usability.flashdroid;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.usability.flashdroid.data_source.CardDataSource;
import com.usability.flashdroid.data_source.DeckDataSource;
import com.usability.flashdroid.model.Card;
import com.usability.flashdroid.model.Deck;
import com.usability.flashdroid.model.Log;
import com.usability.flashdroid.model.Settings;
import com.usability.flashdroid.model.Stat;

public class StudyActivity extends Activity {

	private Deck currentDeck;
	private DeckDataSource deckSource;
	private CardDataSource cardSource;
	private ArrayList<Card> cards;
	
	private int currentCardIndex = 0;
	private boolean termSideUp = true;
	private boolean firstFlip = true;
	
	private Date startMoment;
	private int numReFlips = 0;
	
	private long timeRemaining = 0;
	private CountDownTimer timer;
	private final Random randomColor = new Random();
	private int currentColor = -1;
	private final int[] cardColorFront = new int[] {
			R.drawable.index_card_front_red, 
			R.drawable.index_card_front_green,
			R.drawable.index_card_front_blue
	};
	
	private final int[] cardColorBack = new int[] {
			R.drawable.index_card_back_red, 
			R.drawable.index_card_back_green,
			R.drawable.index_card_back_blue
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        
        startMoment = new Date();
        
        Settings.getInstance();
		timer = new CountDownTimer(Settings.getStudySessionDuration(), 1000) {
			
			@Override
			public void onTick(long millisUntilFinished) {
				final TextView countdown = (TextView) findViewById(R.id.countdown);
				countdown.setText(Util.convertMillisecondsToTimeString(millisUntilFinished));
				
				timeRemaining = millisUntilFinished;
			}
			
			@Override
			public void onFinish() {
				endSession(false);
				
			}
		}.start();
        
        this.deckSource = new DeckDataSource(this);
        this.cardSource = new CardDataSource(this);
                
        Bundle extras = getIntent().getExtras();
        long id = extras.getLong("deckId");
        
        updateCardColor();
        setTermView(true);
        
        deckSource.open();
        this.currentDeck = deckSource.getDeckById(id);
        deckSource.close();
        
        cardSource.open();
    	
        final Cursor cardCursor = cardSource.getCardsByDeckId(this.currentDeck.getId());
    	cards = cursorToList(cardCursor);
    	cardCursor.close();
    	
    	cardSource.close();
    	
    	if (cards.size() <= 0) {
    		Context context = getApplicationContext();
    		CharSequence text = "Please select a deck with at least 1 card.";
    		int duration = Toast.LENGTH_LONG;
    		
    		Toast toast = Toast.makeText(context, text, duration);
    		toast.show();
    		
    		Intent newIntent = new Intent(StudyActivity.this, SelectDeckToStudyActivity.class);
    		StudyActivity.this.startActivity(newIntent);
    		
    		return;
    	}
    	
    	final TextView termView = (TextView) findViewById(R.id.termText);
    	termView.setText(cards.get(currentCardIndex).getTerm());
    	
    	final View.OnClickListener nextHandler = new View.OnClickListener() {
			
			public void onClick(View v) {
				nextCard();
			}
		};
    	
    	final TextView nextText = (TextView) findViewById(R.id.nextText);
    	final ImageButton nextButton = (ImageButton) findViewById(R.id.nextButton);
    	
    	nextText.setOnClickListener(nextHandler);
    	nextButton.setOnClickListener(nextHandler);
    	
    	final View.OnClickListener flipHandler = new View.OnClickListener() {
			
			public void onClick(View v) {
				flip();
			}
		};
    	
    	final TextView flipText = (TextView) findViewById(R.id.flipText);
    	final ImageButton flipButton = (ImageButton) findViewById(R.id.flipButton);
    	
    	flipText.setOnClickListener(flipHandler);
    	flipButton.setOnClickListener(flipHandler);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_study, menu);
        return true;
    }
    
    private void nextCard() {
    	currentCardIndex++;
    	updateCardColor();
    	if (currentCardIndex >= cards.size()) {
    		endSession(false);
    	}
    	else {
    		final TextView termView = (TextView) findViewById(R.id.termText);
    		termView.setText(cards.get(currentCardIndex).getTerm());
    		setTermView(true);
    	}
    	
    }
    
	private void flip() {
    	setTermView(!termSideUp);
    	final TextView termView = (TextView) findViewById(R.id.termText);
     	
    	if (termSideUp) {
    		termView.setText(cards.get(currentCardIndex).getTerm());
    	}
    	else {
    		termView.setText(cards.get(currentCardIndex).getDefinition());
    		
    		if (!firstFlip) {
    			numReFlips++;
    		}
    		else {
    			firstFlip = false;
    		}
    	}
    }
    
    private void setTermView(final boolean termSide) {
    	this.termSideUp = termSide;
    	
    	final TextView termView = (TextView) findViewById(R.id.termText);
    	final ImageView indexCard = (ImageView) findViewById(R.id.indexCard);
    	
    	if (this.termSideUp) {
    		termView.setTextSize(35);
    		System.out.println(currentColor);
    		if (currentColor == -1 || !Settings.isAlternatingCardColors()) {
    			indexCard.setImageResource(R.drawable.index_card_front);
    		}
    		else {
    			indexCard.setImageResource(cardColorFront[currentColor]);
    		}
    	}
    	else {
    		termView.setTextSize(16);
    		if (currentColor == -1 || !Settings.isAlternatingCardColors()) {
    			indexCard.setImageResource(R.drawable.index_card_back);
    		}
    		else {
    			indexCard.setImageResource(cardColorBack[currentColor]);
    		}
    	}
    	
    }
    
    private void endSession(final boolean early) {
    	if (early) {
    		Intent newIntent = new Intent(this, MainActivity.class);
        	startActivity(newIntent);
        	timer.cancel();
    	}
    	else {
    		Log.getinstance();
    		
			// TODO: Show the stats screen and create the stats object
    		final int statId = Log.getAllStats().size();
    		final String deckName = currentDeck.getName();
    		final long timeTaken = Settings.getStudySessionDuration() - timeRemaining + 2000;
    		
    		final Stat s = new Stat(statId, deckName, timeTaken, currentCardIndex, numReFlips, startMoment);
    		Log.getinstance().addStat(s);
    		
    		Intent intent = new Intent(StudyActivity.this, StatisticsBreakdownActivity.class);
			intent.putExtra("statId", statId);
			intent.putExtra("studySession", true);
			startActivity(intent);
    	}
    }
    
    private void updateCardColor() {
    	if (Settings.isAlternatingCardColors()) {
        	currentColor = randomColor.nextInt(cardColorFront.length);
        }
    }
    
    @Override
    public void onBackPressed() {
    	final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
    	
    	dialog.setTitle("Are you sure?");
    	dialog.setMessage("Are you sure you want to end the study session early?");
    	
    	dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
    	
    	dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				endSession(true);
			}
		});
    	
    	dialog.create().show();
    }
    
    
    private ArrayList<Card> cursorToList(final Cursor c) {
    	final ArrayList<Card> list = new ArrayList<Card>();
    	for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
    		final long id = c.getLong(0);
    		final long deckId = c.getLong(1);
    		final String term = c.getString(2);
    		final String definition = c.getString(3);
    		
    		final Card card = new Card(id, deckId, term, definition);
    		
    		list.add(card);
    	}
    	
    	return list;
    }
}
