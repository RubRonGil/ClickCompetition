package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.services;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Player;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Team;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.PlayerRepository;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    //Servicio para actualizar la imágen de un jugador
    public boolean imagePlayerStore (MultipartFile file, Long id) throws IOException {
        String fileName = id.toString() + "_" + file.getOriginalFilename();
        Path targetPath = Paths.get(".\\images\\players\\"+fileName).normalize();
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        Player player= playerRepository.findPlayersById(id).
                orElseThrow(() -> new EntityNotFoundException(id.toString()));
        player.setImgUrl("/download/player/"+fileName);
        playerRepository.save(player);
        return false;
    }

    //Servicio para actualizar la imágen de un team
    public boolean imageTeamStore (MultipartFile file, Long team_id) throws IOException {
        String fileName = team_id.toString() + "_" + file.getOriginalFilename();
        Path targetPath = Paths.get(".\\images\\teams\\"+fileName).normalize();
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        Team team= teamRepository.findTeamsById(team_id).
                orElseThrow(() -> new EntityNotFoundException(team_id.toString()));
        team.setImageUrl("/download/team/"+fileName);
        teamRepository.save(team);
        return false;
    }
}
