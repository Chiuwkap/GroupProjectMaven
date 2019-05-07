package com.project.playlist.dataaccess;

import javax.ejb.Local;

import com.project.playlist.domain.Track;

@Local
public interface PlaylistDataAccess {
	public void insert(Track track);
}
