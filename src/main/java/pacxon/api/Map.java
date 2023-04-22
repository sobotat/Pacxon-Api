package pacxon.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Map {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int sizeX;
    private int sizeY;
}
