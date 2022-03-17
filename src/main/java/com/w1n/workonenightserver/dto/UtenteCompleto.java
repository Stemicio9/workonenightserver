package com.w1n.workonenightserver.dto;

import com.w1n.workonenightserver.model.Azienda;
import com.w1n.workonenightserver.model.DatiUtente;
import com.w1n.workonenightserver.model.Utente;

import java.util.Date;
import java.util.List;

public class UtenteCompleto {


    private String uid;

    private String email;

    private String ruolo;

    private String nomeutente;

    private String status;

    private String descrizione;

    private Date fineabbonamento;

    private List<Azienda> listaaziende;

    private List<String> listaskill;

    private String numerotelefono;

    public UtenteCompleto(){}

    public UtenteCompleto(Utente utente, DatiUtente datiUtente){
        this.uid = utente.getUid();
        this.email = utente.getEmail();
        this.ruolo = utente.getRuolo();
        this.nomeutente = datiUtente.getNomeutente();
        this.status = datiUtente.getStatus();
        this.fineabbonamento = datiUtente.getFineabbonamento();
        this.descrizione = datiUtente.getDescrizione();
        this.numerotelefono = datiUtente.getNumerotelefono();
    }

    public Date getFineabbonamento() {
        return fineabbonamento;
    }

    public String getStatus() {
        return status;
    }

    public String getNomeutente() {
        return nomeutente;
    }

    public String getUid() {
        return uid;
    }

    public String getRuolo() {
        return ruolo;
    }

    public String getEmail() {
        return email;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public List<Azienda> getListaaziende() {
        return listaaziende;
    }

    public String getNumerotelefono() {
        return numerotelefono;
    }

    public List<String> getListaskill() {
        return listaskill;
    }

    public void setFineabbonamento(Date fineabbonamento) {
        this.fineabbonamento = fineabbonamento;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNomeutente(String nomeutente) {
        this.nomeutente = nomeutente;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setListaaziende(List<Azienda> listaaziende) {
        this.listaaziende = listaaziende;
    }

    public void setNumerotelefono(String numerotelefono) {
        this.numerotelefono = numerotelefono;
    }

    public void setListaskill(List<String> listaskill) {
        this.listaskill = listaskill;
    }

    public void aggiungiparteutente(Utente utente){
        this.email = utente.getEmail();
        this.ruolo = utente.getRuolo();
        this.uid = utente.getUid();
    }

    public void aggiungipartedatiutente(DatiUtente datiUtente){
        this.nomeutente = datiUtente.getNomeutente();
        this.status = datiUtente.getStatus();
        this.fineabbonamento = datiUtente.getFineabbonamento();
        this.descrizione = datiUtente.getDescrizione();
        this.listaaziende = datiUtente.getListaaziende();
        this.numerotelefono = datiUtente.getNumerotelefono();
        this.listaskill = datiUtente.getListaskill();
    }

    public Utente parteutente(){
        Utente result = new Utente();
        result.setEmail(this.email);
        result.setRuolo(this.ruolo);
        result.setUid(this.uid);
        return result;
    }

    public DatiUtente partedatiutente(){
        DatiUtente result = new DatiUtente();
        result.setNomeutente(this.nomeutente);
        result.setUid(this.uid);
        result.setStatus(this.status);
        result.setFineabbonamento(this.fineabbonamento);
        result.setDescrizione(this.descrizione);
        result.setListaaziende(this.listaaziende);
        result.setNumerotelefono(this.numerotelefono);
        result.setListaskill(this.listaskill);
        return result;
    }
}
