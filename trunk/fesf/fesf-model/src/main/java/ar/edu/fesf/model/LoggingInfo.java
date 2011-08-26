package ar.edu.fesf.model;

import ar.edu.fesf.application.Entity;
import ar.edu.fesf.validations.ValidatorRequiredField;
import ar.edu.fesf.validations.ValidatorString;

public class LoggingInfo extends Entity {

    private String pass;

    private String userid;

    /* Accessors */
    public String getPass() {
        return this.pass;
    }

    public void setPass(final String pass) {
        ValidatorRequiredField.validate(pass, "Password");
        ValidatorString.validate(pass, "Password");
        this.pass = pass;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(final String userid) {
        ValidatorRequiredField.validate(userid, "User Id");
        ValidatorString.validate(userid, "User Id");
        this.userid = userid;
    }

}
