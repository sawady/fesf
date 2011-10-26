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

    public UserInfo(final String userid, final String pass, final Role role) {
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

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (this.pass == null ? 0 : this.pass.hashCode());
        result = prime * result + (this.role == null ? 0 : this.role.hashCode());
        result = prime * result + (this.userid == null ? 0 : this.userid.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        UserInfo other = (UserInfo) obj;
        if (this.pass == null) {
            if (other.pass != null) {
                return false;
            }
        } else if (!this.pass.equals(other.pass)) {
            return false;
        }
        if (this.role != other.role) {
            return false;
        }
        if (this.userid == null) {
            if (other.userid != null) {
                return false;
            }
        } else if (!this.userid.equals(other.userid)) {
            return false;
        }
        return true;
    }

}
