package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.controllers;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Team;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.TeamRepository;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

@RestController
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/team")
    public ResponseEntity<Object> teamList() {
        Iterable<Team> teams= teamRepository.findAll();
        return new ResponseEntity<>(teams,HttpStatus.OK);
    }

    @GetMapping(value = "/team/{id}")
    public ResponseEntity<Object> teamDetail(@PathVariable("id") Long id) {
        return new ResponseEntity<>(teamRepository.findById(id).orElseThrow(EntityNotFoundException::new),
                                    HttpStatus.OK);
    }

    @GetMapping(value = "/team/clasification")
    public ResponseEntity<Object> bestTeamss() {
        return new ResponseEntity<>(teamRepository.teamsOrderByClicks(), HttpStatus.OK);
    }

    //AÑADIR IMAGEN A TEAM
    @PutMapping(value  = "/team/image/{id}")
    public ResponseEntity<Object> studentImageUpdate(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) throws EntityNotFoundException, IOException {
        imageService.imageTeamStore(file,id);
        return new ResponseEntity<>("Imágen del equipo " + id + " actualizada", HttpStatus.OK);
    }
}
