package ar.edu.fesf.services;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.fesf.model.Person;
import ar.edu.fesf.model.UserInfo;
import ar.edu.fesf.repositories.UserInfoRepository;

public class AuthenticationService {

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

    @Transactional
    public UserInfo findUserInfo(final String pattern) {
        return this.getUserInfoRepository().findUserInfo(pattern);
    }

}
