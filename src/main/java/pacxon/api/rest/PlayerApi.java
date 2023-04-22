package pacxon.api.rest;

import lombok.extern.log4j.Log4j2;
import pacxon.api.Bonus;
import pacxon.api.Player;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Log4j2
@Path("/player")
public class PlayerApi {

    @Inject
    EntityManager entityManager;

    @POST
    @Path("create")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public long createPlayer(Player player){
        try{
            entityManager.persist(player);
        }catch (Exception e){
            log.error(e.getMessage());
            return -1;
        }
        return player.getId();
    }

    @POST
    @Path("get")
    @Transactional
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Player createPlayer(long id){
        return entityManager.find(Player.class, id);
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Player> getPlayers(){
        return entityManager.createQuery("SELECT p FROM Player p", Player.class).getResultList();
    }
}
