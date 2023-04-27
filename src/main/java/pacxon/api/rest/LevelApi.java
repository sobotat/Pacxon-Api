package pacxon.api.rest;

import lombok.extern.log4j.Log4j2;
import pacxon.api.Level;
import pacxon.api.MapBonus;
import pacxon.api.NPC;

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
    @Path("createNew")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createNewLevel(Level level){
        try{
            for(MapBonus mapBonus : level.getBonuses()){
                entityManager.persist(mapBonus.getBonus());
                entityManager.persist(mapBonus);
            }

            for(NPC npc : level.getNpcs()){
                entityManager.persist(npc);
            }

            entityManager.persist(level.getPlayer());
            entityManager.persist(level.getMap());

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
        log.debug(id);
        try {
            Level level = entityManager.createQuery("SELECT l FROM Level l WHERE id LIKE :p_id", Level.class).setParameter("p_id", id).getSingleResult();
            log.debug(level);

            return level;
        }catch (Exception e){
            log.error("Failed to load Level with Id " + id + "  " + e.getMessage());
        }

        return null;
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Level> getLevels(){
        return entityManager.createQuery("SELECT l FROM Level l", Level.class).getResultList();
    }

    @GET
    @Path("/getCount")
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public int getNumberOfLevel(){
        return entityManager.createQuery("SELECT l FROM Level l", Level.class).getResultList().size();
    }
}
