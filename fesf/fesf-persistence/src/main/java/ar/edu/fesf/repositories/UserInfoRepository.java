package ar.edu.fesf.repositories;

import java.io.Serializable;

import ar.edu.fesf.model.UserInfo;

public class UserInfoRepository extends HibernateGenericDAO<UserInfo> implements Serializable {

    private static final long serialVersionUID = 3645468755882083382L;

    @Override
    protected Class<UserInfo> getDomainClass() {
        return UserInfo.class;
    }

    public UserInfo findUserInfo(final String userid) {
        return this.findByPropertyUnique("userid", userid);
    }

}
