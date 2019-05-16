package com.project.playlist;

import java.util.List;

import javax.ejb.Local;

import com.project.playlist.domain.Track;

@Local
public interface PlaylistServiceLocal {
	public void registerTrack(Track track);
	public void deleteTrack(int id);
	public void changeTrack(int id, String[] trackValues);
	public void changeTitle(int id, String title);
	public void changeArtist(int id, String artist);
	public void changeAlbum(int id, String album);
	public List<Track> getAllTracks();
	public List<Track> searchByTitle(String title);
	public List<Track> searchByArtist(String artist);
	public List<Track> searchByAlbum(String album);
}
