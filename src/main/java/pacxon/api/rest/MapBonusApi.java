package pacxon.api.rest;

import lombok.extern.log4j.Log4j2;
import pacxon.api.Bonus;
import pacxon.api.Map;
import pacxon.api.MapBonus;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Log4j2
@Path("/map-bonus")
public class MapBonusApi {

    @Inject
    EntityManager entityManager;

    @POST
    @Path("create")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public long createMapBonus(MapBonus mapBonus){
        try{
            entityManager.persist(mapBonus);
        }catch (Exception e){
            log.error(e.getMessage());
            return -1;
        }
        return mapBonus.getId();
    }

    @POST
    @Path("createAll")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createMapBonuses(List<MapBonus> mapBonuses){
        try {
            for (MapBonus bonus : mapBonuses)
                entityManager.persist(bonus);
        }catch (Exception e){
            log.error(e.getMessage());
            return "Error";
        }
        return "Success";
    }

    @POST
    @Path("get")
    @Transactional
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public MapBonus createMapBonus(long id){
        return entityManager.find(MapBonus.class, id);
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MapBonus> getMapBonuses(){
        return entityManager.createQuery("SELECT b FROM MapBonus b", MapBonus.class).getResultList();
    }
}
