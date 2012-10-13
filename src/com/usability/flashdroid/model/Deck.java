package com.usability.flashdroid.model;

public class Deck {
	
	private long id;
	private String name;
	
	public Deck(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
