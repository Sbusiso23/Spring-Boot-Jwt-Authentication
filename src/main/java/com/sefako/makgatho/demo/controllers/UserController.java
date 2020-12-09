package com.sefako.makgatho.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sefako.makgatho.demo.config.JwtTokenUtil;
import com.sefako.makgatho.demo.models.AuthenticationRequest;
import com.sefako.makgatho.demo.models.AuthenticationResponse;
import com.sefako.makgatho.demo.models.User;
import com.sefako.makgatho.demo.services.MyUserDetailsService;
import com.sefako.makgatho.demo.services.MyUserService;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	MyUserDetailsService userDetailsService;
	
	@Autowired
	MyUserService userService;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	
	@Autowired
	AuthenticationManager authManager;
	
	@PostMapping(path="/authenticate", consumes="application/json")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception
	{
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			
		} catch (BadCredentialsException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect email or password", e);
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		
		String jwtToken = jwtTokenUtil.generateToken(userDetails);
		
		AuthenticationResponse response = new AuthenticationResponse();
		response.setJwtToken(jwtToken);
		
		return (ResponseEntity<?>) ResponseEntity.ok(response);
	}
	
	
	@PostMapping(path="/authenticate", consumes="application/x-www-form-urlencoded")
	public ResponseEntity<?> createAuthToken(AuthenticationRequest request) throws Exception
	{
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			
		} catch (BadCredentialsException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect email or password", e);
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		
		String jwtToken = jwtTokenUtil.generateToken(userDetails);
		
		AuthenticationResponse response = new AuthenticationResponse();
		response.setJwtToken(jwtToken);
		
		return (ResponseEntity<?>) ResponseEntity.ok(response);
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user)
	{
		userService.save(user);
		
		return new ResponseEntity<String>("User registered!", HttpStatus.CREATED);
	}
}
