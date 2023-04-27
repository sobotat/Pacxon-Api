package pacxon.api.rest;

import lombok.extern.log4j.Log4j2;
import pacxon.api.Map;
import pacxon.api.Player;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Log4j2
@Path("/map")
public class MapApi {

    @Inject
    EntityManager entityManager;

    @POST
    @Path("create")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public long createMap(Map map){
        try{
            entityManager.persist(map);
        }catch (Exception e){
            log.error(e.getMessage());
            return -1;
        }
        return map.getId();
    }

    @POST
    @Path("/get")
    @Transactional
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Map getMap(long id){
        return entityManager.find(Map.class, id);
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map> getMaps(){
        return entityManager.createQuery("SELECT m FROM Map m", Map.class).getResultList();
    }
}
