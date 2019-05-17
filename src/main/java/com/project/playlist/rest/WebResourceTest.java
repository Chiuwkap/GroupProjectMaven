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
@Consumes({"application/JSON"})
@Produces({"application/JSON"})
public class WebResourceTest {

    @Inject
    private PlaylistServiceLocal service;


    @POST
    @Path("/add")
    @Consumes({"application/x-www-form-urlencoded"})
    @Produces({"text/plain"})
//    public void registerTrack(Track track) {
//
//        service.registerTrack(track);
//    }
    public Response registerTrack(@FormParam("title") String title, @FormParam("artist") String artist,
                                  @DefaultValue("") @FormParam("album") String album,
                                  @DefaultValue("") @FormParam("genre") String genre,
                                  @DefaultValue("") @FormParam("length") String length) {
        Track t1 = new Track(artist, title, genre, album, length);
        service.registerTrack(t1);
        return Response.status(202).entity("title: " + title + " artist: " + artist).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes({"application/JSON"})
//    @Produces({"application/JSON"})
    @Produces({"text/plain"})
    public Response deleteTrack(@PathParam("id") int id) {

        service.deleteTrack(id);
        return Response.status(202).entity("Track is deleted (ID: " + id).build();
    }

    @POST
    @Path("/update/{id}")
    @Consumes({"application/JSON"})
    @Produces({"application/JSON"})
    public void changeTrack(@PathParam("id") int id, String[] trackValues) {

        service.changeTrack(id, trackValues);
    }

    @POST
    @Path("/update/{id}")
    @Consumes({"application/JSON"})
    @Produces({"application/JSON"})
    public void changeTitle(@PathParam("id") int id, String title) {

        service.changeTitle(id, title);
    }

    @POST
    @Path("/update/{id}")
    @Consumes({"application/JSON"})
    @Produces({"application/JSON"})
    public void changeArtist(@PathParam("id") int id, String artist) {

        service.changeArtist(id, artist);
    }

    @POST
    @Path("/update/{id}")
    @Consumes({"application/JSON"})
    @Produces({"application/JSON"})
    public void changeAlbum(@PathParam("id") int id, String album) {

        service.changeAlbum(id, album);
    }

    @GET
    @Path("/all-track")
    @Produces({"text/plain"})
//    @Produces({"application/JSON"})
//    public List<Track> getAllTracks() {
//        return service.getAllTracks();
//    }
    public Response getAllTracks() {
        List<Track> list = service.getAllTracks(); // for loop?
        return Response.status(202).entity(list).build();
    }

    @GET
    @Consumes({"application/x-www-form-urlencoded"})
    @Path("/search")
//    @Produces({"application/JSON"})
//    public List<Track> search(@Context UriInfo info) {
//        String title = info.getQueryParameters().getFirst("title");
//        String artist = info.getQueryParameters().getFirst("artist");
//        String album = info.getQueryParameters().getFirst("album");
//        if (title != null) {
//            return service.searchByTitle(title);
//        } else if (artist != null) {
//            return service.searchByArtist(artist);
//        } else if (album != null) {
//            return service.searchByAlbum(album);
//        }
//        return null;
//
//    }
    @Produces({"text/plain"})
    public Response search(@QueryParam("title") String title,
                           @QueryParam("artist") String artist,
                           @QueryParam("album") String album/*,
                           @DefaultValue("")@FormParam("genre") String genre*/) {
        List<Track> list = new ArrayList<>();
        if(!(title.isEmpty())) {
            list = service.searchByTitle(title);
        } else if (!(artist.isEmpty())) {
            list = service.searchByArtist(artist);
        } else if (!(album.isEmpty())) {
            list = service.searchByAlbum(album);
        }
        return Response.status(202).entity("Your search result: \n" + list).build();
    }

}