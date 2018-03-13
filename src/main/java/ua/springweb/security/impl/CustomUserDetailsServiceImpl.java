package ua.springweb.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.springweb.security.entity.UserEntity;
import ua.springweb.security.mapper.UserMapper;
import ua.springweb.security.repository.UserRepository;

@Service("userDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	@Autowired UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity entity = userRepository.findUserAutorization(email);
		if(entity == null) throw new UsernameNotFoundException("User not found");
		
		return UserMapper.toSecurityUser(entity);
	}

}
