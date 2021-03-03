package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private long clicks;

    //Relación M-1 con Country
    @ManyToOne
    @JoinColumn()
    private Country country;

    //Relación 1-M con Location
    @JsonBackReference //junto a @EqualsAndHashCode.Exclude evita el bucle infinito mostrando los datos
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "region",cascade = CascadeType.ALL)
    Set<Location> location_Name = new HashSet<>();

    public Region() {
    }

    public Region(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    //Constructor para devolver al cliente la lista ordenada por clicks
    public Region(Region oldRegion, Long clicks) {
        this.id = oldRegion.getId();
        this.name = oldRegion.getName();
        this.country = oldRegion.getCountry();
        this.clicks = clicks;
    }

}
