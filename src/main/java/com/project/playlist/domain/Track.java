package com.project.playlist.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Track")
public class Track implements Serializable{

	@Id
	@GeneratedValue
	private int id;

	@Column
	private String artist;
	@Column
	private String title;
	@Column
	private String genre;
	@Column
	private String album;
	@Column
	private String length;

	public Track() {}
	public Track(String artist, String title, String genre, String album, String length) {
		this.artist = artist;
		this.title = title;
		this.genre = genre;
		this.album = album;
		this.length = length;

	}

	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
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
		return "ID: " + id + ", Artist: " + artist + ", Title: " + title + ", Genre: " + genre + ", Album: " + album + ", Length: " + length;

	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}

}
