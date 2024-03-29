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
@Path("/webservice")
public class WebResource {

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
        return Response.status(202).entity("Track added\nTitle: " + title + ", Artist: " + artist).build();
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

    @POST
    @Path("/update")
    @Consumes({"application/x-www-form-urlencoded"})
    @Produces({"text/plain"})
    public Response change(@FormParam("id") int id, @FormParam("input") String[] input) {
    	for (String s : input) {
    		System.out.println(s);
    	}
		if (!input[0].isEmpty()) {
			service.changeArtist(id, input[0]);
			return Response.status(202).entity("Updated artist").build();
		} else if (!input[1].isEmpty()) {
    		service.changeTitle(id, input[1]);
            return Response.status(202).entity("Updated title").build();
    	} else if (!input[2].isEmpty()) {
    		service.changeAlbum(id, input[2]);
            return Response.status(202).entity("Updated album").build();
    	}
		return Response.status(404).entity("Not found").build();
    }

    @GET
    @Path("/all-track")
    @Produces({"application/JSON"})
    public Response getAllTracks() {
        List<Track> list = service.getAllTracks();
        return Response.status(202).entity(list).build();
    }

//    WebResource använder JAX-RS som REST-implementation
//    Metoden search() anropar olika metoder beroende på vilket fält
//    i webbformuläret som har en sträng. Om t. ex. title inte är tom
//    anropas service.searchByTitle().
    @GET
    @Consumes({"application/x-www-form-urlencoded"})
    @Path("/search")
    @Produces({"application/JSON"})
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
        return Response.status(202).entity(list).build();
    }

}