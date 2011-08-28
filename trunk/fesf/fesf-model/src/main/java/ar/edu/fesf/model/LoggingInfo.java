package ar.edu.fesf.model;

import ar.edu.fesf.application.Entity;
import ar.edu.fesf.validations.NotNullFieldValidator;
import ar.edu.fesf.validations.NotEmptyStringValidator;

public class LoggingInfo extends Entity {

    private String pass;

    private String userid;

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
