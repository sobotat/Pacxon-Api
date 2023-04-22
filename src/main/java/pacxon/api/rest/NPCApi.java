package pacxon.api.rest;

import lombok.extern.log4j.Log4j2;
import pacxon.api.Bonus;
import pacxon.api.NPC;
import pacxon.api.Player;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Log4j2
@Path("/npc")
public class NPCApi {

    @Inject
    EntityManager entityManager;

    @POST
    @Path("create")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public long createNPC(NPC npc){
        try{
            entityManager.persist(npc);
        }catch (Exception e){
            log.error(e.getMessage());
            return -1;
        }
        return npc.getId();
    }

    @POST
    @Path("createAll")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createNPCs(List<NPC> npcs){
        try {
            for (NPC npc : npcs)
                entityManager.persist(npc);
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
    public NPC createNPC(long id){
        return entityManager.find(NPC.class, id);
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NPC> getNPCs(){
        return entityManager.createQuery("SELECT n FROM NPC n", NPC.class).getResultList();
    }
}
