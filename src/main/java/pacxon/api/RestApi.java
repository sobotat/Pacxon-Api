package pacxon.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api")
public class RestApi {

    @GET
    @Path("/status")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean getStatus(){
        return true;
    }
}
