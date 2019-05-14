package com.project.playlist;

import java.util.List;

import javax.ejb.Local;

import com.project.playlist.domain.Track;

@Local
public interface PlaylistServiceLocal {
	public void registerTrack(Track track);
	public List<Track> getAllTracks();
	public List<Track> searchByTitle(String title);
	public void deleteTrack(int id);

}
