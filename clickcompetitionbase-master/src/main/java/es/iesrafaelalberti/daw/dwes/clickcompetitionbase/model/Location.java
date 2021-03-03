package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long clicks;

    //Relación M-1 con Region
    @ManyToOne
    @JoinColumn()
    private Region region;

    //Relación 1-M con Player
    @JsonBackReference //junto a @EqualsAndHashCode.Exclude evita el bucle infinito mostrando los datos
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "location",cascade = CascadeType.ALL)
    Set<Player> player_Name = new HashSet<>();

    public Location() {
    }

    public Location(String name, Region region) {
        this.name = name;
        this.region = region;
    }

    //Constructor para devolver al cliente la lista ordenada por clicks
    public Location(Location oldLocation, Long clicks) {
        this.id = oldLocation.getId();
        this.name = oldLocation.getName();
        this.region = oldLocation.getRegion();
        this.clicks = clicks;
    }
}
