package com.w1n.workonenightserver.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.w1n.workonenightserver.model.DatiUtente;
import com.w1n.workonenightserver.model.Utente;
import com.w1n.workonenightserver.repository.DatiUtenteRepository;
import com.w1n.workonenightserver.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificheService {

    @Autowired
    private FirebaseMessaging firebaseMessaging;

    @Autowired
    private DatiUtenteRepository datiUtenteRepository;

    @Autowired
    private UtenteRepository utenteRepository;

/*    public NotificheService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    } */


    public void mandanotificadiannunciotipuointeressare(String nomeutente){
        String oggetto = "ANNUNCIO PUBBLICATO";
        String contenuto = "QUALCUNO HA PUBBLICATO UN ANNUNCIO CHE POTREBBE INTERESSARTI";
        Map<String,String> data = new HashMap<>();
        List<DatiUtente> lista = datiUtenteRepository.findAllByNomeutenteContains(nomeutente);
        for(DatiUtente current : lista) {
            Utente utente = utenteRepository.findByUid(current.getUid());
            String token = utente.getFirebasemessagetoken();
            try {
                sendNotification(oggetto, contenuto, data, token);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public String sendNotification(String oggetto, String contenuto, Map<String,String> data , String token) throws FirebaseMessagingException {

        Notification notification = Notification
                .builder()
                .setTitle(oggetto)
                .setBody(contenuto)
                .build();

        Message message = Message
                .builder()
                .setToken(token)
                .setNotification(notification)
                .putAllData(data)
                .build();

        return firebaseMessaging.send(message);
    }

}
