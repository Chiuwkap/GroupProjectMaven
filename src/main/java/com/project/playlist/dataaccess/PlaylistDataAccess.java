package com.project.playlist.dataaccess;

import java.util.List;

import javax.ejb.Local;

import com.project.playlist.domain.Track;

@Local
public interface PlaylistDataAccess {
	public void insert(Track track);
	public List<Track> findAllTracks();
	public List<Track> findByTitle(String title);
	public void removeTrack(int id);
}
