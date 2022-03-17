package com.w1n.workonenightserver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "datiutente")
public class DatiUtente {

    @Id
    private String uid;

    private String nomeutente;

    private String status;

    private Date fineabbonamento;

    private String descrizione;

    private String numerotelefono;

    @DBRef
    private List<Azienda> listaaziende;


    private List<String> listaskill;

    public String getUid() {
        return uid;
    }

    public String getNomeutente() {
        return nomeutente;
    }

    public String getStatus() {
        return status;
    }

    public Date getFineabbonamento() {
        return fineabbonamento;
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

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setNomeutente(String nomeutente) {
        this.nomeutente = nomeutente;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setFineabbonamento(Date fineabbonamento) {
        this.fineabbonamento = fineabbonamento;
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
}
