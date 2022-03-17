package com.w1n.workonenightserver.service;


import com.w1n.workonenightserver.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ImmaginiProfiloService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Value("${mievar.percorsosalvataggioimmaginiprofilo}")
    private String percorsosalvataggioimmaginiprofilo;




    public void salvafile(MultipartFile file,String uid) {

        String cartella = percorsosalvataggioimmaginiprofilo+uid;
        String continuazionepath = uid+"/"+uid+"."+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String pathcompleto = percorsosalvataggioimmaginiprofilo+continuazionepath;
        Path pathcompletoaspath = Paths.get(pathcompleto);
        creacartelle(cartella);
        try {
              file.transferTo(pathcompletoaspath);
        } catch (Exception e) {
             e.printStackTrace();
        }
    }


    public Resource prendifile(String uid) {
        String searchdirectory = percorsosalvataggioimmaginiprofilo+"/"+uid;
        Path root = Paths.get(searchdirectory);
        try {
           Path file = find(uid, searchdirectory);


                Resource resource = new UrlResource(file.toUri());
                if (resource.exists() || resource.isReadable()) {
                    return resource;
                } else {
                    throw new RuntimeException("Impossibile leggere il file");
                }

        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        } catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    protected Path find(String fileName, String searchDirectory) throws Exception{
       File dir = new File(searchDirectory);
       File[] files = dir.listFiles(new FilenameFilter() {
           @Override
           public boolean accept(File dir, String name) {
               return name.startsWith(fileName + ".");
           }
       });
       if(files.length > 0){
           return files[0].toPath();
       }else{
           //@todo
           // Inserire l'avatar di default
           throw new Exception("ciao");
       }
    }



    public void creacartelle(String path){
        Path to = Paths.get(path);
        if(!Files.exists(to)) {
            try {
                Files.createDirectories(to);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }



}
