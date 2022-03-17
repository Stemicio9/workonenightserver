package com.w1n.workonenightserver.service;

import com.w1n.workonenightserver.dto.UtenteCompleto;
import com.w1n.workonenightserver.model.Annuncio;
import com.w1n.workonenightserver.model.Skill;
import com.w1n.workonenightserver.model.Utente;
import com.w1n.workonenightserver.repository.AnnunciRepository;
import com.w1n.workonenightserver.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class AnnunciService {

    @Autowired
    private AnnunciRepository annunciRepository;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private SkillRepository skillRepository;



    public List<Skill> queryskills(String query){
        if(query.isEmpty()){
            return skillRepository.findAll();
        }
        return skillRepository.findAllByNomeskillRegex(".*"+query+".*");

    }

    public boolean aggiungiskill(Skill skill){
        try{
            skillRepository.save(skill);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean pubblicaannuncio(Annuncio annuncio, String idpubblicante){
        try{
            Utente utente = utenteService.fromid(idpubblicante);
            annuncio.setPubblicanteid(utente);
            annunciRepository.save(annuncio);
            // @todo
            // MANDARE LE NOTIFICHE A TUTTI QUELLI A CUI L'ANNUNCIO POTREBBE INTERESSARE
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }



// PER I DATORI DI LAVORO
    // ANNUNCIO ATTIVO = DI UNA DATA FUTURA + NON MATCHATO
    // ANNUNCIO ACCETTATO = DI UNA DATA FUTURA + MATCHATO
    // IF ACCETTATI == TRUE STO CERCANDO  GLI ANNUNCI ACCETTATI, ALTRIMENTI TUTTI GLI ANNUNCI ATTIVI
    public List<Annuncio> findannunci(String userid,boolean accettati){
        UtenteCompleto utenteCompleto = utenteService.me(userid);
        Date data = new Date();
        // QUESTA E' LA LISTA CON MATCHATI E NON MATCHATI
        List<Annuncio> listaannunci = annunciRepository.findAllByPubblicanteidAndDataAfter(utenteCompleto.parteutente(),data);
        List<Annuncio> result = filtramatchatinonmatchati(accettati,listaannunci);
        return result;
    }

    // STORICO = DI UNA DATA PRECEDENTE
    public List<Annuncio> findstorico(String userid){
        UtenteCompleto utenteCompleto = utenteService.me(userid);
        Date data = new Date();
        List<Annuncio> listaannunci = annunciRepository.findAllByPubblicanteidAndDataBefore(utenteCompleto.parteutente(),data);
        return listaannunci;
    }


    // true = matchati, false = non matchati
    private List<Annuncio> filtramatchatinonmatchati(boolean matchati, List<Annuncio> listaannunci){
        List<Annuncio> result = new LinkedList<>();
        for(Annuncio curr: listaannunci){
            if(matchati){
                if(curr.getUtentescelto() != null){
                    result.add(curr);
                }
            }else{
                if(curr.getUtentescelto() == null){
                    result.add(curr);
                }
            }
        }
        return result;
    }



    // PER IL LAVORATORE
    public List<Annuncio> ricercaannunci(String uid, List<String> skill,
                                         Date mindata, Date maxdata,
                                         double lat, double lng, double distanza,
                                         int oramin, int oramax,
                                         double pagamin, double pagamax){
        List<Annuncio> result = new LinkedList<>();



        return result;
    }

}
