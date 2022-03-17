package com.w1n.workonenightserver.controller;

import com.w1n.workonenightserver.model.DatiUtente;
import com.w1n.workonenightserver.model.Utente;
import com.w1n.workonenightserver.repository.DatiUtenteRepository;
import com.w1n.workonenightserver.repository.UtenteRepository;
import com.w1n.workonenightserver.service.NotificheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("notifiche")
public class NotificheController {

    @Autowired
    private NotificheService notificheService;

    @Autowired
    private DatiUtenteRepository datiUtenteRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @GetMapping("notificaprova/{nomeutente}")
    public String mandanotificaprova(@PathVariable String nomeutente){
        String oggetto = "ANNUNCIO PUBBLICATO";
        String contenuto = "QUALCUNO HA PUBBLICATO UN ANNUNCIO CHE POTREBBE INTERESSARTI";
        Map<String,String> data = new HashMap<>();
        List<DatiUtente> lista = datiUtenteRepository.findAllByNomeutenteContains(nomeutente);
        int numnotifiche = 0;
        for(DatiUtente current : lista) {
            Utente utente = utenteRepository.findByUid(current.getUid());
            String token = utente.getFirebasemessagetoken();
            try {
                notificheService.sendNotification(oggetto, contenuto, data, token);
                numnotifiche++;
            } catch (Exception e) {
                e.printStackTrace();
                return "ERRORE NEL MANDARE LA NOTIFICA:  " + e.getMessage();
            }
        }
        return "NOTIFICHE INVIATE CON SUCCESSO: " + numnotifiche;
    }






}
