package com.w1n.workonenightserver.model;

import org.springframework.data.annotation.Id;

public class Azienda {

    @Id
    private String uid;

    private String nomeazienda;
    private String nomelocation;
    private String numerocivico;
    private PosizioneLatLong posizionelatlong;

    public String getNumerocivico() {
        return numerocivico;
    }

    public String getNomeazienda() {
        return nomeazienda;
    }

    public PosizioneLatLong getPosizionelatlong() {
        return posizionelatlong;
    }

    public String getNomelocation() {
        return nomelocation;
    }

    public String getUid() {
        return uid;
    }

    public void setNumerocivico(String numerocivico) {
        this.numerocivico = numerocivico;
    }

    public void setNomeazienda(String nomeazienda) {
        this.nomeazienda = nomeazienda;
    }

    public void setNomelocation(String nomelocation) {
        this.nomelocation = nomelocation;
    }

    public void setPosizionelatlong(PosizioneLatLong posizionelatlong) {
        this.posizionelatlong = posizionelatlong;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof Azienda)) return false;
        Azienda other = (Azienda) obj;
        return other.getUid().equals(this.uid);
    }
}
