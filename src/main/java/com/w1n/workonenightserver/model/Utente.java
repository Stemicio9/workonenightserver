package com.w1n.workonenightserver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "utente")
public class Utente {

    @Id
    private String uid;

    private String email;

    private String ruolo;

    private String firebasetoken;

    private String firebasemessagetoken;

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getRuolo() {
        return ruolo;
    }

    public String getFirebasetoken() {
        return firebasetoken;
    }

    public String getFirebasemessagetoken() {
        return firebasemessagetoken;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public void setFirebasetoken(String firebasetoken) {
        this.firebasetoken = firebasetoken;
    }

    public void setFirebasemessagetoken(String firebasemessagetoken) {
        this.firebasemessagetoken = firebasemessagetoken;
    }

    @Override
    public String toString() {
        return this.email;
    }
}
