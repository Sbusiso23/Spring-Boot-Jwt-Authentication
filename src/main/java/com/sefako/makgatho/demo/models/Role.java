package com.sefako.makgatho.demo.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	@OneToMany(mappedBy="role")
	private List<User> users;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<User> getUsers(){
		return this.users;
	}
}
