package com.sefako.makgatho.demo.services;

import com.sefako.makgatho.demo.models.User;

public interface UserService {
	public void save(User user);
	
	User findUserByEmail(String email);
}
