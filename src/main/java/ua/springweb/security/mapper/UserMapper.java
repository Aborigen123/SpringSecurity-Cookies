package ua.springweb.security.mapper;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import ua.springweb.security.domain.RegisterRequest;
import ua.springweb.security.entity.UserEntity;
import ua.springweb.security.enumeration.Role;

public interface UserMapper {

	public static User toSecurityUser(UserEntity entity) {//перетворення юзера в юзера який надає нам springsecurity
		
		
		return new User(
				entity.getEmail(),
				entity.getPassword(),
				AuthorityUtils.createAuthorityList(String.valueOf(entity.getRole()))//читаємо роль юзера
				);
		
	}
	
	
	public static UserEntity registerToUser(RegisterRequest request) {
	UserEntity entity = new UserEntity();
	entity.setEmail(request.getEmail());
	entity.setPassword(request.getPassword());
	entity.setRole(Role.ROLE_USER);
	
	
return entity;	
	
}}
