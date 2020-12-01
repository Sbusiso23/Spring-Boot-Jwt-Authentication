package com.sefako.makgatho.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sefako.makgatho.demo.models.Role;
import com.sefako.makgatho.demo.repositories.RoleRepository;

@RestController
public class RolesController {

	@Autowired
	RoleRepository roleRepository;
	
	@PostMapping("/roles")
	public ResponseEntity<String> store(@RequestBody Role role)
	{
		roleRepository.save(role);
		
		return new ResponseEntity<String>("Role created!", HttpStatus.CREATED);
	}
	
	@GetMapping("/roles")
	public Iterable<Role> index()
	{
		Iterable<Role> roles = roleRepository.findAll();
		return roles;
	}
}
