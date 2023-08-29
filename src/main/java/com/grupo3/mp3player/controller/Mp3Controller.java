package com.grupo3.mp3player.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/mp3")
public class Mp3Controller {

    @GetMapping("/{songName}")
    public ResponseEntity<byte[]> getMp3(@PathVariable String songName) throws IOException {
        Resource resource = new ClassPathResource("mp3/" + songName + ".mp3");
        byte[] mp3Bytes = Files.readAllBytes(Path.of(resource.getURI()));

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(mp3Bytes);
    }
}
