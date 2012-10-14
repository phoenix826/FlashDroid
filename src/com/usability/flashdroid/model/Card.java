package com.usability.flashdroid.model;

public class Card {
	
	private long id;
	private long deckId;
	private String term;
	private String definition;
	
	public Card() {
		
	}
	
	public Card(long id, long deckId, String term, String definition) {
		this.id = id;
		this.deckId = deckId;
		this.term = term;
		this.definition = definition;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setDeckId(long deckId) {
		this.deckId = deckId;
	}
	
	public void setTerm(String term) {
		this.term = term;
	}
	
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	public long getId() {
		return this.id;
	}
	
	public long getDeckId() {
		return this.deckId;
	}
	
	public String getTerm() {
		return this.term;
	}
	
	public String getDefinition() {
		return this.definition;
	}
	
}
