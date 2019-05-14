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
	public void updateAll(int id, String[] trackValues) {
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
	public List<Track> findAllTracks() {
		Query q = manager.createQuery("SELECT track FROM Track track");
		List<Track> tracks = q.getResultList();

		return tracks;
	}

	@Override
	public List<Track> findByTitle(String title) {
		Query q = manager.createQuery("select track from Track track where title like :title");
		q.setParameter("title", "%" + title + "%");
		List<Track> tracks = q.getResultList();
		return tracks;
	}

	@Override
	public List<Track> findByArtist(String artist) {
		Query q = manager.createQuery("select track from Track track where artist= :artist");
		q.setParameter("artist", "%" + artist + "%");
		List<Track> tracks = q.getResultList();
		return tracks;
	}

	@Override
	public List<Track> findByAlbum(String album) {
		Query q = manager.createQuery("select track from Track track where album= :album");
		q.setParameter("album", "%" + album+ "%");
		List<Track> tracks = q.getResultList();
		return tracks;
	}


}
