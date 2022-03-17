package com.w1n.workonenightserver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "annuncio")
public class Annuncio {

    @Id
    private String uid;

    @DBRef
    private Azienda azienda;

    private Date data;


    private int ora;

    private int minuto;

    private String mansione;

    @DBRef
    private Utente pubblicanteid;

    private String noteaggiuntive;

    @DBRef
    private List<Utente> listacandidati;

    @DBRef
    private Utente utentescelto;

    private double paga;

    public Azienda getAzienda() {
        return azienda;
    }

    public String getUid() {
        return uid;
    }

    public Date getData() {
        return data;
    }

    public double getPaga() {
        return paga;
    }

    public List<Utente> getListacandidati() {
        return listacandidati;
    }

    public String getMansione() {
        return mansione;
    }

    public String getNoteaggiuntive() {
        return noteaggiuntive;
    }

    public Utente getUtentescelto() {
        return utentescelto;
    }

    public Utente getPubblicanteid() {
        return pubblicanteid;
    }

    public int getOra() {
        return ora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setListacandidati(List<Utente> listacandidati) {
        this.listacandidati = listacandidati;
    }

    public void setMansione(String mansione) {
        this.mansione = mansione;
    }

    public void setNoteaggiuntive(String noteaggiuntive) {
        this.noteaggiuntive = noteaggiuntive;
    }

    public void setPaga(double paga) {
        this.paga = paga;
    }

    public void setPubblicanteid(Utente pubblicanteid) {
        this.pubblicanteid = pubblicanteid;
    }

    public void setUtentescelto(Utente utentescelto) {
        this.utentescelto = utentescelto;
    }

    public void setOra(int ora) {
        this.ora = ora;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }
}
