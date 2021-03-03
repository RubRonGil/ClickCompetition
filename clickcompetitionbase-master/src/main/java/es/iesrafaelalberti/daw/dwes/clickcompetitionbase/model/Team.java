package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long clicks;
    private String imageUrl;

    //Relacion M-N con Player
    @ManyToMany
    @JoinTable(
            name = "player_team",
            joinColumns = { @JoinColumn(name = "team_id") },
            inverseJoinColumns = { @JoinColumn(name = "player_id") }
    )

    private List<Player> players;

    public Team() { }

    public Team(String name) {
        this.name = name;
        this.players = new ArrayList<>();
    }

    public Team addPlayers(List<Player> players){
        this.getPlayers().addAll(players);
        return this;
    }

    //Constructor para devolver al cliente la lista ordenada por clicks
    public Team(Team oldTeam, Long clicks) {
        this.id = oldTeam.getId();
        this.name = oldTeam.getName();
        this.players = oldTeam.getPlayers();
        this.clicks = clicks;
    }
}
