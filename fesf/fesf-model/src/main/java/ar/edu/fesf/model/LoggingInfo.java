package ar.edu.fesf.model;

import ar.edu.fesf.application.Entity;

public class LoggingInfo extends Entity {

    private String pass;

    private String userid;

    /* Accessors */
    public String getPass() {
        return this.pass;
    }

    public void setPass(final String pass) {
        this.pass = pass;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(final String userid) {
        this.userid = userid;
    }

}
