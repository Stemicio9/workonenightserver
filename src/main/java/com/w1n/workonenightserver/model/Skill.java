package com.w1n.workonenightserver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "skill")
public class Skill {

    @Id
    private String uid;

    private String nomeskill;
    private String urlimmagine;


    public String getUid() {
        return uid;
    }

    public String getNomeskill() {
        return nomeskill;
    }

    public String getUrlimmagine() {
        return urlimmagine;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setNomeskill(String nomeskill) {
        this.nomeskill = nomeskill;
    }

    public void setUrlimmagine(String urlimmagine) {
        this.urlimmagine = urlimmagine;
    }
}
