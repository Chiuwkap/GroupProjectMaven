package com.project.playlist.dataaccess;

import java.util.List;

import javax.ejb.Local;

import com.project.playlist.domain.Track;

@Local
public interface PlaylistDataAccess {
	public void insert(Track track);
	public void removeTrack(int id);
	public void updateAll(int id, String[] trackValues);
	public void updateTitle(int id, String title);
	public void updateArtist(int id, String artist);
	public void updateAlbum(int id, String album);
	public List<Track> findAllTracks();
	public List<Track> findByTitle(String title);
	public List<Track> findByArtist(String artist);
	public List<Track> findByAlbum(String album);
}
