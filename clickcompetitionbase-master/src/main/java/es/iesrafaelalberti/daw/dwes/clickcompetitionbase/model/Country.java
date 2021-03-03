package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long clicks;

    //Relación 1-M con Region
    @JsonBackReference //junto a @EqualsAndHashCode.Exclude evita el bucle infinito mostrando los datos
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "country",cascade = CascadeType.ALL)
    Set<Region> region_Name = new HashSet<>();

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    //Constructor para devolver al cliente la lista ordenada por clicks
    public Country(Country oldCountry, Long clicks) {
        this.id = oldCountry.getId();
        this.name = oldCountry.getName();
        this.clicks = clicks;
    }
}
