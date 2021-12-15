package it.springsecurity.users;

public class Role {
	
	private Integer id;

	private String name;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
