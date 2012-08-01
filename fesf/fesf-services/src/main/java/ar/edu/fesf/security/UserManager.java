package ar.edu.fesf.security;

import ar.edu.fesf.dtos.PersonDTOMapper;
import ar.edu.fesf.model.Person;
import ar.edu.fesf.services.PersonService;

public class UserManager {
	
	private PersonService personService;

	public void signUp(UserDetailsLdapImpl userDatails) {
		Person person = this.personService.findPersonByEmail(userDatails.getEmail());
		if(person == null) {
			this.personService.registerNewPerson(new PersonDTOMapper().map(userDatails));
		}
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	
	

}
