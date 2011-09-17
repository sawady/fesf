package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import ar.edu.fesf.application.Entity;

public class UserInfo extends Entity {

    private String pass;

    private String userid;

    private Permission permission;

    /* Accessors */
    public String getPass() {
        return this.pass;
    }

    public void setPass(final String pass) {
        checkNotNull(pass);
        checkArgument(!pass.isEmpty());
        this.pass = pass;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(final String userid) {
        checkNotNull(userid);
        checkArgument(!userid.isEmpty());
        this.userid = userid;
    }

    public Permission getPermission() {
        return this.permission;
    }

    public void setPermission(final Permission permission) {
        this.permission = permission;
    }

}
