package com.project.playlist.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;

import com.project.playlist.PlaylistServiceLocal;
import com.project.playlist.domain.Track;

@Stateless
@Path("/")
public class TrackResource {

  @Inject
  private PlaylistServiceLocal service;

  @GET
  public List<Track> getAllTracks() {
    return service.getAllTracks();
  }

	// public List<Track> searchByTitle() {
  //   return null;
  // }

}
