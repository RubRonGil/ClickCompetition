package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int clicks;
    private String imgUrl;

    //Relación M-1 con Location
    @ManyToOne
    @JoinColumn()
    private Location location;

    //Relacion M-N con Team    https://www.baeldung.com/jpa-many-to-many    https://stackoverflow.com/questions/42394095/many-to-many-relationship-between-two-entities-in-spring-boot/42396995
    @JsonBackReference
    @ManyToMany(mappedBy = "players")
    private List<Team> teams;



    public Player() {
    }

    public Player(String name, int clicks, Location location) {
        this.name = name;
        this.clicks = clicks;
        this.location = location;
    }

    public void addClicks(int clicks) {
        this.clicks += clicks;
    }
}
