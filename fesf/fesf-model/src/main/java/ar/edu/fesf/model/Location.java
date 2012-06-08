package ar.edu.fesf.model;

public class Location extends Entity {
	
	private static final long serialVersionUID = 1L;
	private String name;
	
	public Location() {}
	
	public Location(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
