package com.fse.utils;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetails implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username.equalsIgnoreCase("seller")) {
			return new User("seller", "SellerPass", Arrays.asList(new SimpleGrantedAuthority("ROLE_SELLER")));
		} else if (username.equalsIgnoreCase("buyer")) {
			return new User("buyer", "BuyerPass", Arrays.asList(new SimpleGrantedAuthority("ROLE_BUYER")));
		}
		throw new UsernameNotFoundException("User not found with" + username);
	}

}
