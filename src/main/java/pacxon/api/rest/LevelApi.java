package pacxon.api.rest;

import lombok.extern.log4j.Log4j2;
import pacxon.api.Level;
import pacxon.api.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Log4j2
@Path("/level")
public class LevelApi {

    @Inject
    EntityManager entityManager;

    @POST
    @Path("create")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createLevel(Level level){
        try{
            entityManager.persist(level);
        }catch (Exception e){
            log.error(e.getMessage());
            return "-1";
        }
        return level.getId();
    }

    @POST
    @Path("get")
    @Transactional
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Level getLevel(String id){
        return entityManager.find(Level.class, id);
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Level> getLevels(){
        return entityManager.createQuery("SELECT l FROM Level l", Level.class).getResultList();
    }
}
