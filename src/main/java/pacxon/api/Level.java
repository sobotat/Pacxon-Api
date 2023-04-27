package pacxon.api;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Level {

    @Id
    private String id;

    @OneToOne
    Map map;

    @OneToOne
    Player player;

    @ManyToMany
    List<NPC> npcs;

    @ManyToMany
    List<MapBonus> bonuses;
}
