package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class UserInfo extends Entity {

    private String pass;

    private String userid;

    private Role role;

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

    public Role getRole() {
        return this.role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

}
