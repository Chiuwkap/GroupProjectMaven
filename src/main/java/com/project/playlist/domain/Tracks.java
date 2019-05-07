package com.project.playlist.domain;

import java.io.Serializable;

public class Tracks implements Serializable{

	private String artist;
	private String title;
	private String genre;
	private String album;
	private String length;
		
	public Tracks(String artist, String title, String genre, String album, String length) {
		this.artist = artist;
		this.title = title;
		this.genre = genre;
		this.album = album;
		this.length = length;
		
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String toString() {
		return artist + " " + title;
		
	}
	
}
