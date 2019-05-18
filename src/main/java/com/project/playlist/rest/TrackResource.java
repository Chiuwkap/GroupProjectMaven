package com.project.playlist.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
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
  public Response registerTrack(Track track) {

    service.registerTrack(track);
    return Response.status(202).entity("title: " + track.getTitle() + " artist: " + track.getArtist()).build();
  }

  @DELETE
  @Path("/delete/{id}")
  public Response deleteTrack(@PathParam("id") int id) {

    service.deleteTrack(id);
    return Response.status(202).entity("Track is deleted (ID: " + id + ")").build();
  }
  
  @PUT
  @Consumes({"application/JSON"})
  @Path("/updatetrack/{id}")
  public Response changeTrack(@PathParam("id") int id, String[] input) {
	  service.changeTrack(id, input);
	  return Response.status(202).entity("Track is updated (ID: " + id + ")").build();
  }
  
  
  @PUT
  @Consumes({"application/JSON"})
  @Path("/update/{id}/{part}")
  public Response change(@PathParam("id") int id, @PathParam("part") String part, String input) {
	  
	  if (part.equals("title")) {
		  service.changeTitle(id, input);
		  return Response.status(202).entity("Updated title").build();
	  } else if (part.equals("artist")) {
		  service.changeArtist(id, input);
		  return Response.status(202).entity("Updated artist").build();
	  } else if (part.equals("album")) {
		  service.changeAlbum(id, input);
		  return Response.status(202).entity("Updated album").build();
	  }
	  return Response.status(404).entity("Not found").build();
  }

  @GET
  @Path("/all-track")
  @Produces({"application/JSON"})
  public Response getAllTracks() {

    List<Track> list = service.getAllTracks(); // for loop?
    return Response.status(202).entity(list).build();
  }

  @GET
  @Path("/search")
  @Produces({"application/JSON"})
  public Response search(@Context UriInfo info) {
	  String title = info.getQueryParameters().getFirst("title");
	  String artist = info.getQueryParameters().getFirst("artist");
	  String album = info.getQueryParameters().getFirst("album");
	  List<Track> list = new ArrayList<>();
	  if (title != null) {
		  list = service.searchByTitle(title);
	  } else if (artist != null) {
		  list = service.searchByArtist(artist);
	  } else if (album != null) {
		  list = service.searchByAlbum(album);
	  }
	return Response.status(202).entity(list).build();

  }
}
