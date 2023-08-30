package com.grupo3.mp3player.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.grupo3.mp3player.service.Mp3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/mp3")
public class Mp3Controller {

    @GetMapping("/song/{songName}")
    @CrossOrigin
    public ResponseEntity<byte[]> getMp3(@PathVariable String songName) throws IOException {
        Resource resource = new ClassPathResource("mp3/" + songName);
        byte[] mp3Bytes = Files.readAllBytes(Path.of(resource.getURI()));

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(mp3Bytes);
    }

    @GetMapping("/songs-names")
    @CrossOrigin
    public ResponseEntity<List<String>> getSongsNames() {
        Mp3Service service = new Mp3Service();
        return ResponseEntity.ok().body(service.getSongsNames());
    }
}
