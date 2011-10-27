package ar.edu.fesf.services;

import static com.google.common.base.Preconditions.checkState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.model.Person;
import ar.edu.fesf.model.Role;
import ar.edu.fesf.model.UserInfo;
import ar.edu.fesf.repositories.UserInfoRepository;

public class AuthenticationService implements Serializable, IAuthenticationService {

    private static final long serialVersionUID = 1L;

    private UserInfoRepository userInfoRepository;

    private PersonService personService;

    public PersonService getPersonService() {
        return this.personService;
    }

    public void setPersonService(final PersonService personService) {
        this.personService = personService;
    }

    public UserInfoRepository getUserInfoRepository() {
        return this.userInfoRepository;
    }

    public void setUserInfoRepository(final UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean authenticate(final String userid, final String password) {

        UserInfo userinfo = this.findUserInfo(userid);

        // usuario no encontrado
        // if (userinfo == null) {
        // throw new AuthenticationException("Invalid userid");
        // }

        // el password no es valido
        // if (!userinfo.getPass().equals(password)) {
        // throw new AuthenticationException("Invalid password");
        // }

        return userinfo != null && userinfo.getPass().equals(password);

    }

    public Role[] getRoles() {
        return Role.values();
    }

    @Transactional(readOnly = true)
    public Person findPersonWithUserInfo(final String userid) {
        return this.getPersonService().findPersonWithUserInfo(this.findUserInfo(userid));
    }

    @Transactional(readOnly = true)
    public UserInfo findUserInfo(final String userid) {
        return this.getUserInfoRepository().findUserInfo(userid);
    }

    @Transactional
    public void addUserInfo(final UserInfo userinfo) {
        UserInfo existingUserInfo = this.getUserInfoRepository().findUserInfo(userinfo.getUserid());
        checkState(existingUserInfo == null, "Userid already exists");
        this.getUserInfoRepository().save(userinfo);
    }

    public List<String> getRolesOf(final Person person) {

        List<String> roles = new ArrayList<String>();

        Role userRole = person.getUserInfo().getRole();

        for (Role role : Role.values()) {
            if (role.isInferior(userRole)) {
                roles.add(role.toString());
            }
        }

        return roles;
    }

}
