package com.w1n.workonenightserver.configuration.utils;

import lombok.Data;

@Data
public class CustomPrincipal {
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
