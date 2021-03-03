package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.controllers;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/download/player/{name}")
    public ResponseEntity<Resource> getPlayerImage(@PathVariable("name") String name) throws Exception {
        Path targetPath = Paths.get(".\\images\\players\\" + name).normalize();
        try {
            Resource resource = new UrlResource(targetPath.toUri());
            if (resource.exists()) {
                String contentType = Files.probeContentType(targetPath);
                return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
            }
        } catch (IOException ex) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/download/team/{name}")
    public ResponseEntity<Resource> getTeamImage(@PathVariable("name") String name) throws Exception {
        Path targetPath = Paths.get(".\\images\\teams\\" + name).normalize();
        try {
            Resource resource = new UrlResource(targetPath.toUri());
            if (resource.exists()) {
                String contentType = Files.probeContentType(targetPath);
                return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
            }
        } catch (IOException ex) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
