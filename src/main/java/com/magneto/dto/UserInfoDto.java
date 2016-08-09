package com.magneto.dto;

import com.magneto.location.Geolocation;

public class UserInfoDto {
	private int id;
	private String name;
	private Geolocation location;
	private boolean sex;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Geolocation getLocation() {
		return location;
	}

	public void setLocation(Geolocation location) {
		this.location = location;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

}
