package pacxon.api.rest;

import lombok.extern.log4j.Log4j2;
import pacxon.api.Bonus;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Log4j2
@Path("/bonus")
public class BonusApi {

    @Inject
    EntityManager entityManager;

    @POST
    @Path("create")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public long createBonus(Bonus bonus){
        try{
            entityManager.persist(bonus);
        }catch (Exception e){
            log.error(e.getMessage());
            return -1;
        }
        return bonus.getId();
    }

    @POST
    @Path("createAll")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createBonuses(List<Bonus> bonuses){
        try {
            for (Bonus bonus : bonuses)
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
    public Bonus createBonus(long id){
        return entityManager.find(Bonus.class, id);
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bonus> getBonuses(){
        return entityManager.createQuery("SELECT b FROM Bonus b", Bonus.class).getResultList();
    }
}
