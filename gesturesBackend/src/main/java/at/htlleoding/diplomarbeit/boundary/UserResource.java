package at.htlleoding.diplomarbeit.boundary;

import at.htlleoding.diplomarbeit.model.User;
import at.htlleoding.diplomarbeit.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/user")
@Transactional
public class UserResource {

    @Inject
    UserRepository userRepository;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public List<User> getList(){
        return userRepository.listAll();
    }


}
