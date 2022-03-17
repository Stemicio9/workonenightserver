package com.w1n.workonenightserver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recensioniutente")
public class RecensioniUtente {

    @Id
    private String uid;

    private long match;

    private long valutazionipositive;

    private long blacklist;

    public String getUid() {
        return uid;
    }

    public long getBlacklist() {
        return blacklist;
    }

    public long getMatch() {
        return match;
    }

    public long getValutazionipositive() {
        return valutazionipositive;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setBlacklist(long blacklist) {
        this.blacklist = blacklist;
    }

    public void setMatch(long match) {
        this.match = match;
    }

    public void setValutazionipositive(long valutazionipositive) {
        this.valutazionipositive = valutazionipositive;
    }
}
