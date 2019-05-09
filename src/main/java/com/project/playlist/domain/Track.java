package com.project.playlist.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Track implements Serializable{

	@Id
	@GeneratedValue
	private int id;
	
	private String artist;
	private String title;
	private String genre;
	private String album;
	private String length;
	
	public Track() {}
	public Track(String artist, String title, String genre, String album, String length) {
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
