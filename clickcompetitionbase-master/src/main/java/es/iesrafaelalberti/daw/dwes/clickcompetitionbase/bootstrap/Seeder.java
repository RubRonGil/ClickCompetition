package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.bootstrap;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.*;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Seeder implements CommandLineRunner {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public void run(String... args) throws Exception {
        //Seeders Country
        Country c1 = countryRepository.save(new Country("España"));
        Country c2 = countryRepository.save(new Country("Inglaterra"));
        Country c3 = countryRepository.save(new Country("Alemania"));

        //Seeders Region
        Region r1 = regionRepository.save(new Region("Andalucía", c1));
        Region r2 = regionRepository.save(new Region("Madrid",c1));
        Region r3 = regionRepository.save(new Region("Cataluña", c1));
        Region r4 = regionRepository.save(new Region("London", c2));
        Region r5 = regionRepository.save(new Region("Berlin", c3));

        //Seeders Location
        Location l1 = locationRepository.save(new Location("Cádiz", r1));
        Location l2 = locationRepository.save(new Location("Málaga", r1));
        Location l3 = locationRepository.save(new Location("Madrid",r2));
        Location l4 = locationRepository.save(new Location("Salou", r3));
        Location l5 = locationRepository.save(new Location("Harrow",r4));
        Location l6 = locationRepository.save(new Location("Potsdam", r5));



        //Seeders Players
        Player p1 = playerRepository.save(new Player("Javier Ortega", 2,l1));
        Player p2 = playerRepository.save(new Player("Rubén Rondán", 10,l2));
        Player p3 = playerRepository.save(new Player("Merche Fernández", 22, l3));
        Player p4 = playerRepository.save(new Player("Jordi kbsa", 7, l4));
        Player p5 = playerRepository.save(new Player("William Hill", 13,l5));
        Player p6 = playerRepository.save(new Player("Frankfurt Jaeger", 2, l6));

        //Seeders Teams
        Team t1 = teamRepository.save(new Team("Cádiz CT").addPlayers(Arrays.asList(p1,p2)));
        Team t2 = teamRepository.save(new Team("Málaga CT").addPlayers(Arrays.asList(p2,p3)));
        Team t3 = teamRepository.save(new Team("Madrid CT").addPlayers(Arrays.asList(p2,p3,p4)));
        Team t4 = teamRepository.save(new Team("Salou CT")).addPlayers(Arrays.asList(p4));
        Team t5 = teamRepository.save(new Team("Harrow CT").addPlayers(Arrays.asList(p5)));
        Team t6 = teamRepository.save(new Team("Potsdam CT").addPlayers(Arrays.asList(p5,p6)));
    }
}
