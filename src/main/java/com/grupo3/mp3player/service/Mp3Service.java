package com.grupo3.mp3player.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Mp3Service {

    public List<String> getSongsNames() {
        List<String> songsNames = new ArrayList<>();
        String folderPath = "src/main/resources/mp3";

        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".mp3")) {
                    songsNames.add(file.getName());
                }
            }
        } else {
            System.out.println("A pasta não existe ou não é um diretório.");
        }
        return songsNames;
    }
}
