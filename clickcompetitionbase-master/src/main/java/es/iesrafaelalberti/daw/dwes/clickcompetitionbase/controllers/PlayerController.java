package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.controllers;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Player;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.PlayerRepository;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

@RestController
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/player")
    public ResponseEntity<Object> playerList() {
        return new ResponseEntity<>(playerRepository.findAll(),HttpStatus.OK);
    }

    @GetMapping(value = "/player/{id}")
    public ResponseEntity<Object> playerDetail(@PathVariable("id") Long id) {
        return new ResponseEntity<>(playerRepository.findById(id).orElseThrow(EntityNotFoundException::new),
                                    HttpStatus.OK);
    }

    @GetMapping(value = "/player/morethan/{clicks}")
    public ResponseEntity<Object> bestPlayersByClick(@PathVariable("clicks") Integer clicks) {
        return new ResponseEntity<>(playerRepository.findPlayerByClicks(clicks), HttpStatus.OK);
    }

    @GetMapping(value = "/player/clasification")
    public ResponseEntity<Object> bestPlayers() {
        return new ResponseEntity<>(playerRepository.playersOrderByClicks(), HttpStatus.OK);
    }

    //AÑADIR IMAGEN A PLAYER
    @PutMapping(value  = "/player/image/{id}")
    public ResponseEntity<Object> studentImageUpdate(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) throws EntityNotFoundException, IOException {
        imageService.imagePlayerStore(file,id);
        return new ResponseEntity<>("Imágen del jugador " + id + " actualizada", HttpStatus.OK);
    }
}
