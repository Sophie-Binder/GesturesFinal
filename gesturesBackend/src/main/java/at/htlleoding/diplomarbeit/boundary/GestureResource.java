package at.htlleoding.diplomarbeit.boundary;

import at.htlleoding.diplomarbeit.model.Gesture;
import at.htlleoding.diplomarbeit.repository.GestureRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.MediaType;

import java.awt.*;
import java.util.List;

@Path("/api/gesture")
@Transactional
public class GestureResource {

    @Inject
    GestureRepository gestureRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public List<Gesture> getList(){
        return gestureRepository.listAll();
    }



}
