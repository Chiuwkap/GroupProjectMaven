package com.project.playlist.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.project.playlist.PlaylistServiceLocal;
import com.project.playlist.domain.Track;

@Stateless
@Path("/")
@Consumes({"application/JSON"})
@Produces({"application/JSON"})
public class TrackResource {

  @Inject
  private PlaylistServiceLocal service;

  @POST
  @Path("/add")
  public void registerTrack(Track track) {

    service.registerTrack(track);
  }

  @DELETE
  @Path("/delete/{id}")
  public void deleteTrack(@PathParam("id") int id) {

    service.deleteTrack(id);
  }

  // TODO: fixa update-metoderna
  @POST
  @Path("/update/{id}")
  public void changeTrack(@PathParam("id") int id, String[] trackValues) {

    service.changeTrack(id, trackValues);
  }

  @POST
  @Path("/update/{id}")
  public void changeTitle(@PathParam("id") int id, String title) {

    service.changeTitle(id, title);
  }

  @POST
  @Path("/update/{id}")
  public void changeArtist(@PathParam("id") int id, String artist) {

    service.changeArtist(id, artist);
  }

  @POST
  @Path("/update/{id}")
  public void changeAlbum(@PathParam("id") int id, String album) {

    service.changeAlbum(id, album);
  }

  @GET
  @Path("/all-track")
  @Produces({"application/JSON"})
  public List<Track> getAllTracks() {

    return service.getAllTracks();
  }

  @GET
  @Path("/search")
  @Produces({"application/JSON"})
  public List<Track> search(@Context UriInfo info) {
	  String title = info.getQueryParameters().getFirst("title");
	  String artist = info.getQueryParameters().getFirst("artist");
	  String album = info.getQueryParameters().getFirst("album");
	  if (title != null) {
		  return service.searchByTitle(title);
	  } else if (artist != null) {
		  return service.searchByArtist(artist);
	  } else if (album != null) {
		  return service.searchByAlbum(album);
	  }
	return null;

  }
}
