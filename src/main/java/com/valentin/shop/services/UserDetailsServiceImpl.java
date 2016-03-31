package com.valentin.shop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.valentin.shop.entities.Role;
import com.valentin.shop.entities.User;
import com.valentin.shop.interfaces.UserDao;

public class UserDetailsServiceImpl implements UserDetailsService {

	
	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		User user = userDao.getUserByUsername(username);
		Set<Role> roles = user.getRoles();
		
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRole()));
		}
		
		user.setAuthorities(authorities);
		
		return user;
	}
}
