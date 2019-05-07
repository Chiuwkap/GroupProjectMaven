package com.project.playlist;

import javax.ejb.Local;

import com.project.playlist.domain.Track;

@Local
public interface PlaylistServiceLocal {
	public void registerTrack(Track track);
}
