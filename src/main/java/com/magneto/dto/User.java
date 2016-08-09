package com.magneto.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.magneto.location.Geolocated;
import com.magneto.location.Geolocation;

public class User extends org.springframework.security.core.userdetails.User implements Geolocated<Integer> {

	/**
	 * 
	 */
	private int id;

	private Geolocation location;

	public int getId() {
		return id;
	}

	public User(int id, String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.id = id;

	}

	@Override
	public void setGeolocation(Geolocation location) {
		this.location = location;

	}

	@Override
	public Geolocation getGeoLocation() {
		// TODO Auto-generated method stub
		return location;
	}

	@Override
	public Integer getKey() {
		return id;
	}

}
