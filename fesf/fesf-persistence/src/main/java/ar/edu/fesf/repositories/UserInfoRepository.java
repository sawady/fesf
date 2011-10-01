package ar.edu.fesf.repositories;

import ar.edu.fesf.model.UserInfo;

public class UserInfoRepository extends HibernateGenericDAO<UserInfo> {

    @Override
    protected Class<UserInfo> getDomainClass() {
        return UserInfo.class;
    }

    public UserInfo findUserInfo(final String pattern) {
        return this.findByProperty("userinfo", pattern);
    }

}
