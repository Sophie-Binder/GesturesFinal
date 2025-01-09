package at.htlleoding.diplomarbeit.boundary;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Path("/image")
public class ImageResource {

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleImage(@FormParam("file") InputStream fileInputStream,
                                @FormParam("fileName") String fileName) {
        try {
            // Forward the image to Flask
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofInputStream(() -> fileInputStream);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://127.0.0.1:5000/predict"))
                    .header("Content-Type", "multipart/form-data")
                    .POST(bodyPublisher)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return Response.ok(response.body()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity("Error forwarding image to Flask: " + e.getMessage()).build();
        }
    }
}
