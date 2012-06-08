package ar.edu.fesf.repositories;

import ar.edu.fesf.model.Location;

public class LocationRepository extends HibernateGenericDAO<Location> {

	private static final long serialVersionUID = 1L;

	@Override
	protected Class<Location> getDomainClass() {
		return Location.class;
	}

}
