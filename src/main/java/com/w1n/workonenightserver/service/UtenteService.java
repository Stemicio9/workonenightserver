package com.w1n.workonenightserver.service;

import com.w1n.workonenightserver.dto.UtenteCompleto;
import com.w1n.workonenightserver.model.*;
import com.w1n.workonenightserver.repository.*;
import com.w1n.workonenightserver.responses.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private DatiUtenteRepository datiUtenteRepository;

    @Autowired
    private RecensioniUtenteRepository recensioniUtenteRepository;

    @Autowired
    private AziendaRepository aziendaRepository;

    @Autowired
    private SkillRepository skillRepository;


    public boolean rimuoviazienda(String uid, Azienda azienda){
        try{
            DatiUtente utente = datiUtenteRepository.findByUid(uid);
            utente.getListaaziende().remove(azienda);
            datiUtenteRepository.save(utente);
            aziendaRepository.delete(azienda);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean rimuoviskill(String uid, String skill){
        try{
            DatiUtente utente = datiUtenteRepository.findByUid(uid);
            utente.getListaskill().remove(skill);
            datiUtenteRepository.save(utente);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public String nomeutentedauid(String uid){
        try {
            DatiUtente result = datiUtenteRepository.findByUid(uid);
            return result.getNomeutente();
        }catch(Exception e){
            e.printStackTrace();
            return "Nome utente non inserito";
        }
    }

    public boolean aggiungiazienda(String uid, Azienda azienda){
        try{
            aziendaRepository.save(azienda);
            DatiUtente utente = datiUtenteRepository.findByUid(uid);
            if(utente.getListaaziende() == null){
                utente.setListaaziende(new LinkedList<>());
            }
            utente.getListaaziende().add(azienda);
            try{
                datiUtenteRepository.save(utente);
                return true;
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean aggiungiskill(String uid, String skill){
        try{
            DatiUtente utente = datiUtenteRepository.findByUid(uid);
            if(utente.getListaskill() == null){
                utente.setListaskill(new LinkedList<>());
            }
            utente.getListaskill().add(skill);
            try{
                datiUtenteRepository.save(utente);
                return true;
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public MyResponse registrazione(UtenteCompleto utenteCompleto){
        MyResponse<UtenteCompleto> response = new MyResponse();
        StringBuilder messaggio = new StringBuilder();
        StringBuilder eccezione = new StringBuilder();
        UtenteCompleto completo = new UtenteCompleto();
        int codice = 200;
        try{
            Utente utente = utenteCompleto.parteutente();
            Utente res = utenteRepository.save(utente);
            completo.aggiungiparteutente(res);
        }catch(Exception e){
            eccezione.append(e.getMessage());
            messaggio.append("Non sono riuscito a salvare l'utente");
            codice = 510;
        }
        try{
            DatiUtente datiUtente = utenteCompleto.partedatiutente();
            datiUtente.setFineabbonamento(new Date());
            DatiUtente result = datiUtenteRepository.save(datiUtente);
            completo.aggiungipartedatiutente(result);
        }catch(Exception e){
            eccezione.append(e.getMessage());
            messaggio.append("Non sono riuscito a salvare i datiutente");
            codice = 520;
        }
        response.setCode(codice);
        response.setMessage(messaggio.toString());
        response.setException(eccezione.toString());
        response.setData(completo);
        return response;
    }

    public UtenteCompleto modificadatiutente(UtenteCompleto utenteCompleto){
        Utente utente = utenteRepository.findByUid(utenteCompleto.getUid());
        DatiUtente datiUtente = datiUtenteRepository.findByUid(utenteCompleto.getUid());
        if(utenteCompleto.getNomeutente() != null || !utenteCompleto.getNomeutente().isEmpty()){
            datiUtente.setNomeutente(utenteCompleto.getNomeutente());
        }
        if(utenteCompleto.getStatus() != null || !utenteCompleto.getStatus().isEmpty()){
            datiUtente.setStatus(utenteCompleto.getStatus());
        }
        if(utenteCompleto.getNumerotelefono() != null || !utenteCompleto.getNumerotelefono().isEmpty()){
            datiUtente.setNumerotelefono(utenteCompleto.getNumerotelefono());
        }
        if(utenteCompleto.getDescrizione() != null || !utenteCompleto.getDescrizione().isEmpty()){
            datiUtente.setDescrizione(utenteCompleto.getDescrizione());
        }
        try{
            utenteRepository.save(utente);
            datiUtenteRepository.save(datiUtente);
        }catch(Exception e){
            e.printStackTrace();
        }
        return me(utente.getUid());
    }


    public Utente fromid(String uid){
        return utenteRepository.findByUid(uid);
    }

    public UtenteCompleto me(String uid){
        UtenteCompleto completo = new UtenteCompleto();
        completo.aggiungipartedatiutente(datiUtenteRepository.findByUid(uid));
        completo.aggiungiparteutente(utenteRepository.findByUid(uid));
        return completo;
    }


    public RecensioniUtente getrecensioniutente(String uid){
        verificaesistenzarecensione(uid);
        return recensioniUtenteRepository.findByUid(uid);
    }

    public void aggiungimatch(String uid){
        RecensioniUtente rec = verificaesistenzarecensione(uid);
        rec.setMatch(rec.getMatch() + 1);
        try{
            recensioniUtenteRepository.save(rec);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void aggiungipositivi(String uid){
        RecensioniUtente rec = verificaesistenzarecensione(uid);
        rec.setValutazionipositive(rec.getValutazionipositive() + 1);
        try{
            recensioniUtenteRepository.save(rec);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void aggiungiblacklist(String uid){
        RecensioniUtente rec = verificaesistenzarecensione(uid);
        rec.setBlacklist(rec.getBlacklist() + 1);
        try{
            recensioniUtenteRepository.save(rec);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public RecensioniUtente verificaesistenzarecensione(String uid){
        RecensioniUtente result = recensioniUtenteRepository.findByUid(uid);
        if(result == null){
            RecensioniUtente nuovo = new RecensioniUtente();
            nuovo.setUid(uid);
            nuovo = recensioniUtenteRepository.save(nuovo);
            return nuovo;
        }
        return result;
    }

    public List<UtenteCompleto> getallutenti(){
        List<UtenteCompleto> result = new LinkedList<>();
        for(Utente curr : utenteRepository.findAll()){
            UtenteCompleto daaggiungere = new UtenteCompleto();
            daaggiungere.aggiungiparteutente(curr);
            daaggiungere.aggiungipartedatiutente(datiUtenteRepository.findByUid(curr.getUid()));
            result.add(daaggiungere);
        }
        return result;
    }

    public List<UtenteCompleto> getlistautentifiltered(String filter){
        List<UtenteCompleto> result = new LinkedList<>();
        for(DatiUtente curr : datiUtenteRepository.findAllByNomeutenteContains(filter)){
            UtenteCompleto daaggiungere = new UtenteCompleto();
            daaggiungere.aggiungipartedatiutente(curr);
            daaggiungere.aggiungiparteutente(utenteRepository.findByUid(curr.getUid()));
            result.add(daaggiungere);
        }
        return result;
    }

    public void updatetoken(String firebasetoken, String uid){
        Utente utente = utenteRepository.findByUid(uid);
        if(utente != null) {
            if (utente.getFirebasetoken() == null ||
                    utente.getFirebasetoken().isEmpty() ||
                    !utente.getFirebasetoken().equals(firebasetoken)) {
                utente.setFirebasetoken(firebasetoken);
                utenteRepository.save(utente);
            }
        }
    }

    public void updatefirebasemessagetoken(String firebasemessagetoken, String uid){
        Utente utente = utenteRepository.findByUid(uid);
        if(utente.getFirebasemessagetoken() == null ||
                utente.getFirebasemessagetoken().isEmpty() ||
                !utente.getFirebasemessagetoken().equals(firebasemessagetoken)){
            utente.setFirebasemessagetoken(firebasemessagetoken);
            utenteRepository.save(utente);
        }
    }


    public List<UtenteCompleto> querylavoratori(String query){
        List<DatiUtente> listarisultati = datiUtenteRepository.findAllByNomeutenteRegex(query);
        List<UtenteCompleto> result = new LinkedList<>();
        for(DatiUtente daticurr : listarisultati){
            Utente curr = utenteRepository.findByUid(daticurr.getUid());
            if(curr.getRuolo().equals("datore")){
                continue;
            }
            UtenteCompleto daaggiungere = new UtenteCompleto();
            daaggiungere.aggiungiparteutente(curr);
            daaggiungere.aggiungipartedatiutente(daticurr);
            result.add(daaggiungere);
        }
        return result;
    }




}
