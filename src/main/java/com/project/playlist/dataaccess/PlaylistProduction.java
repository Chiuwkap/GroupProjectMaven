package com.project.playlist.dataaccess;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.project.playlist.domain.Track;

@Stateless
public class PlaylistProduction implements PlaylistDataAccess {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void insert(Track track) {
		manager.persist(track);
		
	}

}
