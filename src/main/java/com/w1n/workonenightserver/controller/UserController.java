package com.w1n.workonenightserver.controller;

import com.w1n.workonenightserver.configuration.utils.CustomPrincipal;
import com.w1n.workonenightserver.dto.UtenteCompleto;
import com.w1n.workonenightserver.model.Azienda;
import com.w1n.workonenightserver.model.RecensioniUtente;
import com.w1n.workonenightserver.model.Utente;
import com.w1n.workonenightserver.responses.MyResponse;
import com.w1n.workonenightserver.service.ImmaginiProfiloService;
import com.w1n.workonenightserver.service.UtenteService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.ch.IOUtil;

import javax.websocket.server.PathParam;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private ImmaginiProfiloService immaginiProfiloService;

    @GetMapping("testcontroller/}")
    public String testcontroller(@AuthenticationPrincipal CustomPrincipal customPrincipal){
        return customPrincipal.getUid();
    }

    @GetMapping("/me")
    public UtenteCompleto me(@AuthenticationPrincipal CustomPrincipal customPrincipal){

        return utenteService.me(customPrincipal.getUid());
    }

    @PostMapping("/register")
    public MyResponse registrati(@RequestBody UtenteCompleto utenteCompleto){
          return utenteService.registrazione(utenteCompleto);
    }

    @PostMapping("/update")
    public UtenteCompleto update(@AuthenticationPrincipal CustomPrincipal customPrincipal, @RequestBody UtenteCompleto utenteCompleto){
        return utenteService.modificadatiutente(utenteCompleto);
    }

    @GetMapping("/myfeedbacks")
    public RecensioniUtente myfeedbacks(@AuthenticationPrincipal CustomPrincipal customPrincipal){
        return utenteService.getrecensioniutente(customPrincipal.getUid());
    }

    @GetMapping("/feedbacks/{uid}")
    public RecensioniUtente getfeedbacks(@AuthenticationPrincipal CustomPrincipal customPrincipal, @PathVariable String uid){
        return utenteService.getrecensioniutente(uid);
    }


    @PostMapping("/updatemessagetoken")
    public String updatefirebasemessagetoken(@AuthenticationPrincipal CustomPrincipal customPrincipal, @RequestBody String token){
        utenteService.updatefirebasemessagetoken(token,customPrincipal.getUid());
        return "AGGIUNTO";
    }

    @PostMapping("/aggiungiazienda")
    public boolean aggiungiazienda(@AuthenticationPrincipal CustomPrincipal customPrincipal, @RequestBody Azienda azienda){
       return utenteService.aggiungiazienda(customPrincipal.getUid(),azienda);
    }

    @PostMapping("/rimuoviazienda")
    public boolean rimuoviazienda(@AuthenticationPrincipal CustomPrincipal customPrincipal, @RequestBody Azienda azienda){
        return utenteService.rimuoviazienda(customPrincipal.getUid(),azienda);
    }

    @GetMapping("/aggiungiskill/{nomeskill}")
    public boolean aggiungiskill(@AuthenticationPrincipal CustomPrincipal customPrincipal, @PathVariable String nomeskill){
        return utenteService.aggiungiskill(customPrincipal.getUid(),nomeskill);
    }

    @GetMapping("/rimuoviskill/{nomeskill}")
    public boolean rimuoviskill(@AuthenticationPrincipal CustomPrincipal customPrincipal, @PathVariable String nomeskill){
        return utenteService.rimuoviskill(customPrincipal.getUid(),nomeskill);
    }


    @GetMapping("/nomeutentedauid/{uid}")
    public String nomeutentedauid(@AuthenticationPrincipal CustomPrincipal customPrincipal, @PathVariable String uid){
        return utenteService.nomeutentedauid(uid);
    }



    // Parte relativa alle immagini del profilo

    @PostMapping("/salvaimmagineprofilo")
    public String handleFileUpload(@AuthenticationPrincipal CustomPrincipal customPrincipal,@RequestParam("file") MultipartFile file) {
         immaginiProfiloService.salvafile(file,customPrincipal.getUid());
         return "Fatto";
    }

    @GetMapping(value = "/immagineprofilo/{uid}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody Resource getimmagineprofilo(@PathVariable String uid){
       Resource file = immaginiProfiloService.prendifile(uid);
       return file;
    }

    @GetMapping(value = "/miaimmagineprofilo",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody Resource miaimmagineprofilo(@AuthenticationPrincipal CustomPrincipal customPrincipal){
       return getimmagineprofilo(customPrincipal.getUid());
    }



    // PARTE AMMINISTRAZIONE

    @GetMapping("getallutenti")
    public List<UtenteCompleto> getallutenti(@AuthenticationPrincipal CustomPrincipal customPrincipal){
         return utenteService.getallutenti();
    }



    // PARTE RICERCA UTENTI

    @GetMapping("querylavoratori/{nomelavoratore}")
    public List<UtenteCompleto> querylavoratori(@PathVariable String nomelavoratore, @AuthenticationPrincipal CustomPrincipal customPrincipal){
        return utenteService.querylavoratori(nomelavoratore);
    }

}
