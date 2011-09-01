package ar.edu.fesf.model;

import ar.edu.fesf.application.Entity;
import ar.edu.fesf.validations.NotEmptyStringValidator;
import ar.edu.fesf.validations.NotNullFieldValidator;

public class LoggingInfo extends Entity {

    private String pass;

    private String userid;

    private Permission permission;

    /* Accessors */
    public String getPass() {
        return this.pass;
    }

    public void setPass(final String pass) {
        NotNullFieldValidator.validate(pass, "Password");
        NotEmptyStringValidator.validate(pass, "Password");
        this.pass = pass;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(final String userid) {
        NotNullFieldValidator.validate(userid, "User Id");
        NotEmptyStringValidator.validate(userid, "User Id");
        this.userid = userid;
    }

}
