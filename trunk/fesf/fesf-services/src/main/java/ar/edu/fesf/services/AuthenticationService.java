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

public class AuthenticationService implements Serializable {

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

    public Person authenticate(final String userid, final String password) {

        Person person;
        UserInfo userinfo = this.findUserInfo(userid);

        // usuario no encontrado
        if (userinfo == null) {
            throw new AuthenticationException("Invalid userid");
        }

        // el password no es valido
        if (!userinfo.getPass().equals(password)) {
            throw new AuthenticationException("Invalid password");
        }

        person = this.getPersonService().findPersonWithUserInfo(userinfo);

        // ninguna persona posee esta informacion de usuario
        if (person == null) {
            throw new AuthenticationException("Cant find person associated with this user");
        }

        return person;

    }

    public Person authenticate(final String userid, final String password, final Role role) {

        Person person;
        UserInfo userinfo = this.findUserInfo(userid);

        // usuario no encontrado
        if (userinfo == null) {
            throw new AuthenticationException("Invalid userid");
        }

        // se quiere loguear con mas privilegios de los que tiene
        if (userinfo.getRole().isInferior(role)) {
            throw new AuthenticationException("You have not such privileges");
        }

        // el password no es valido
        if (!userinfo.getPass().equals(password)) {
            throw new AuthenticationException("Invalid password");
        }

        person = this.getPersonService().findPersonWithUserInfo(userinfo);

        // ninguna persona posee esta informacion de usuario
        if (person == null) {
            throw new AuthenticationException("Cant find person associated with this user");
        }

        return person;

    }

    public Role[] getRoles() {
        return Role.values();
    }

    @Transactional
    public UserInfo findUserInfo(final String pattern) {
        return this.getUserInfoRepository().findUserInfo(pattern);
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
