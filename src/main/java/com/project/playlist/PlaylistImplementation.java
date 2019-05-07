package com.project.playlist;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.project.playlist.dataaccess.PlaylistDataAccess;
import com.project.playlist.domain.Track;

@Stateless
public class PlaylistImplementation implements PlaylistService, PlaylistServiceLocal {
	
	@Inject
	private PlaylistDataAccess dao;
	
	@Override
	public void registerTrack(Track track) {
		dao.insert(track);
	}

}
