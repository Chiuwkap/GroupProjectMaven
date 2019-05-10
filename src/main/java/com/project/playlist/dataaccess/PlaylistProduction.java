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
	public List<Track> findAllTracks() {
		Query q = manager.createQuery("SELECT track FROM Track track");
		List<Track> tracks = q.getResultList();

		return tracks;
	}

	// @Override
	// public List<Track> findByTitle(String title) {
	// 	Query q = manager.createQuery("SELECT track FROM Track track WHERE title = :title;");
	// 	q.setParameter("title", title);
	// 	return q.getResultList();
	// }

}
