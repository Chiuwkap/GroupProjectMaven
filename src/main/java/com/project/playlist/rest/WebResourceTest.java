package com.project.playlist.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.project.playlist.PlaylistServiceLocal;
import com.project.playlist.domain.Track;

@Stateless
@Path("/a")
public class WebResourceTest {

    @Inject
    private PlaylistServiceLocal service;


    @POST
    @Path("/add")
    @Consumes({"application/x-www-form-urlencoded"})
    @Produces({"text/plain"})
    public Response registerTrack(@FormParam("title") String title, @FormParam("artist") String artist,
                                  @DefaultValue("") @FormParam("album") String album,
                                  @DefaultValue("") @FormParam("genre") String genre,
                                  @DefaultValue("") @FormParam("length") String length) {
        Track t1 = new Track(artist, title, genre, album, length);
        service.registerTrack(t1);
        return Response.status(202).entity("title: " + title + " artist: " + artist).build();
    }

    @POST
    @Path("/delete")
    @Consumes({"application/x-www-form-urlencoded"})
    @Produces({"text/plain"})
    public Response deleteTrack(@FormParam("id") int id) {

        service.deleteTrack(id);
        return Response.status(202).entity("Track is deleted (ID: " + id + ")").build();
    }

    @POST
    @Consumes({"application/x-www-form-urlencoded"})
    @Produces({"text/plain"})
    @Path("/updatetrack")
	public Response changeTrack(@FormParam("id") int id, @FormParam("input") String[] input) {
		service.changeTrack(id, input);
		return Response.status(202).entity("Track is updated (ID: " + id + ")").build();
	}

//    @POST
//    @Path("/updatetitle")
//    @Consumes({"application/x-www-form-urlencoded"})
//    @Produces({"text/plain"})
//    public Response changeTitle(@PathParam("id") int id, String title) {
//
//        service.changeTitle(id, title);
//        return Response.status(202).entity("Updated title").build();
//    }
//
//    @POST
//    @Path("/updateartist")
//    @Consumes({"application/x-www-form-urlencoded"})
//    @Produces({"text/plain"})
//    public Response changeArtist(@PathParam("id") int id, String artist) {
//
//        service.changeArtist(id, artist);
//        return Response.status(202).entity("Updated artist").build();
//    }
//
//    @POST
//    @Path("/updatealbum")
//    @Consumes({"application/x-www-form-urlencoded"})
//    @Produces({"text/plain"})
//    public Response changeAlbum(@PathParam("id") int id, String album) {
//
//        service.changeAlbum(id, album);
//        return Response.status(202).entity("Updated album").build();
//    }

    @GET
    @Path("/all-track")
    @Produces({"text/plain"})
    public Response getAllTracks() {
        List<Track> list = service.getAllTracks(); // for loop?
        String output = "";
        for (Track track : list) {
        	output += track.toString();
        }
        return Response.status(202).entity(output).build();
    }

    @GET
    @Consumes({"application/x-www-form-urlencoded"})
    @Path("/search")
    @Produces({"text/plain"})
    public Response search(@QueryParam("title") String title,
                           @QueryParam("artist") String artist,
                           @QueryParam("album") String album) {
        List<Track> list = new ArrayList<>();
        if(!(title.isEmpty())) {
            list = service.searchByTitle(title);
        } else if (!(artist.isEmpty())) {
            list = service.searchByArtist(artist);
        } else if (!(album.isEmpty())) {
            list = service.searchByAlbum(album);
        }
        return Response.status(202).entity("Your search result: \n" + list + "\n ").build();
    }

}