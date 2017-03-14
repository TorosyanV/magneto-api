package com.magneto.web.model;

public class UserInfoResponce {

	private int id;
	private String name;
	private GeolocationModel location;
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

	public GeolocationModel getLocation() {
		return location;
	}

	public void setLocation(GeolocationModel location) {
		this.location = location;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

}
