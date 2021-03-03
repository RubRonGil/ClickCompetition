package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Player;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Optional;

public interface TeamRepository extends CrudRepository<Team, Long> {

    @Query("Select new es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Team(t, sum(p.clicks)) " +
            "from Team t join t.players p " +
            "group by t.id " +
            "order by sum(p.clicks)")
    Collection<Team> teamsOrderByClicks();

    Optional<Team> findTeamsById(Long id);
}
