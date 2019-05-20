package com.project.playlist.dataaccess;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;

import com.project.playlist.domain.Track;

@Stateless
public class PlaylistProduction implements PlaylistDataAccess {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void insert(Track track) {
		manager.persist(track);
	}
	
	@Override
	public void removeTrack(int id) {
		Track track = manager.find(Track.class, id);
		manager.remove(track);
	}
	
	@Override
	public void updateTrack(int id, String[] trackValues) {
		Track track = manager.find(Track.class, id);
//		artist, title, genre, album, length
		track.setArtist(trackValues[0]);
		track.setTitle(trackValues[1]);
		track.setGenre(trackValues[2]);
		track.setAlbum(trackValues[3]);
		track.setLength(trackValues[4]);
		manager.persist(track);
	}
	
	@Override
	public void updateTitle(int id, String title) {
		Track track = manager.find(Track.class, id);
		track.setTitle(title);
		manager.persist(track);
	}
	
	@Override
	public void updateArtist(int id, String artist) {
		Track track = manager.find(Track.class, id);
		track.setArtist(artist);
		manager.persist(track);
	}

	@Override
	public void updateAlbum(int id, String album) {
		Track track = manager.find(Track.class, id);
		track.setAlbum(album);
		manager.persist(track);
		
	}

	@Override
	public List<Track> findAllTracks() {
		Query q = manager.createQuery("SELECT track FROM Track track");
		List<Track> tracks = q.getResultList();

		return tracks;
	}
	
	
//	Här används JPQL för att hämta data från databasen och resultatet returneras som en lista
	@Override
	public List<Track> findByTitle(String title) {
		Query q = manager.createQuery("select track from Track track where title like lower(:title)");
		q.setParameter("title", "%" + title.toLowerCase() + "%");
		List<Track> tracks = q.getResultList();
		return tracks;
	}

	@Override
	public List<Track> findByArtist(String artist) {
		Query q = manager.createQuery("select track from Track track where artist like lower(:artist)");
		q.setParameter("artist", "%" + artist.toLowerCase() + "%");
		List<Track> tracks = q.getResultList();
		return tracks;
	}

	@Override
	public List<Track> findByAlbum(String album) {
		Query q = manager.createQuery("select track from Track track where album like lower(:album)");
		q.setParameter("album", "%" + album.toLowerCase() + "%");
		List<Track> tracks = q.getResultList();
		return tracks;
	}
}
