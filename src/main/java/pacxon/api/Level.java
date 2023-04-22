package pacxon.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Level {

    @Id
    private String id;

    @OneToOne
    Map map;

    @OneToOne
    Player player;

    @ManyToMany
    List<NPC> NPCs;

    @ManyToMany
    List<MapBonus> bonuses;
}
