package ua.springweb.security.service;

import ua.springweb.security.entity.UserEntity;

public interface UserService {

	void saveUser(UserEntity entity);
	
	UserEntity findUserByEmail(String email);
}
