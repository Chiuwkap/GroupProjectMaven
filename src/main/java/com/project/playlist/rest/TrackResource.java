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

  @POST
  @Path("/add/{}")
  public void registerTrack(Track track) {

    service.registerTrack(track);
  }

  @DELETE
  @Path("/delete/{id}")
  public void deleteTrack(@PathParam("id") int id) {

    service.deleteTrack(id);
  }

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
  public List<Track> searchByTitle(@QueryParam("title") String title) {

    return service.searchByTitle(title);
  }

  @GET
  @Path("/search")
  public List<Track> searchByArtist(@QueryParam("artist") String artist) {

    return service.searchByArtist(artist);
  }

  @GET
  @Path("/search/{album}")
  public List<Track> searchByAlbum(@QueryParam("album") String album) {

    return service.searchByAlbum(album);
  }

}
