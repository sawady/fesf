package ar.edu.fesf.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

public class UserInfo extends Entity implements Serializable {

    private static final long serialVersionUID = 3031596603243637940L;

    private String pass;

    private String userid;

    private Role role;

    public UserInfo() {
        super();
    }

    public UserInfo(final String pass, final String userid, final Role role) {
        super();
        this.pass = pass;
        this.userid = userid;
        this.role = role;
    }

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
