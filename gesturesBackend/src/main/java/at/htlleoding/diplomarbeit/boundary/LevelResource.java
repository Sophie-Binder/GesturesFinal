package at.htlleoding.diplomarbeit.boundary;


import at.htlleoding.diplomarbeit.model.Level;
import at.htlleoding.diplomarbeit.repository.LevelRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/level")
@Transactional
public class LevelResource {

    @Inject
    LevelRepository levelRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public List<Level> getList(){
        return levelRepository.listAll();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/{userId}")
    public List<Level> getLevelsByUserId(@PathParam("userId") Long userId) {
        return levelRepository.findByUserId(userId);
    }



}
