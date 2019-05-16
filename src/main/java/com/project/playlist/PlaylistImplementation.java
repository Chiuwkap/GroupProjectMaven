package com.project.playlist;

import java.util.List;

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

	@Override
	public void deleteTrack(int id) {
		dao.removeTrack(id);
	}
	
	@Override
	public void changeAll(int id, String[] trackValues) {
		dao.updateTrack(id, trackValues);
	}
	
	@Override
	public void changeTitle(int id, String title) {
		dao.updateTitle(id, title);
	}
	
	@Override
	public List<Track> getAllTracks() {
		return dao.findAllTracks();
	}

	@Override
	public List<Track> searchByTitle(String title) {
		return dao.findByTitle(title);
	}

	@Override
	public List<Track> searchByArtist(String artist) {
		return dao.findByArtist(artist);
	}

	@Override
	public List<Track> searchByAlbum(String album) {
		return dao.findByAlbum(album);
	}


}
