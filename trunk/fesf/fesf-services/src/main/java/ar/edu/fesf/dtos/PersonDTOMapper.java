package ar.edu.fesf.dtos;

import ar.edu.fesf.security.UserDetailsLdapImpl;

public class PersonDTOMapper {

	public PersonDTO map(UserDetailsLdapImpl userDatails) {
		PersonDTO personDTO = new PersonDTO();
		personDTO.setAddress(userDatails.getAddress());
		personDTO.setAge(18);
		personDTO.setEmail(userDatails.getEmail());
		personDTO.setName(userDatails.getFirstName());
		personDTO.setPhone(userDatails.getPhone());
		personDTO.setSurname(userDatails.getLastName());		
		return personDTO;
	}

}
