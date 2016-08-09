package com.magneto.data.dataobject;

import java.util.Date;

import com.magneto.data.entity.UserEntity;

/**
 * Created by vazgen on 2/23/16.
 */
public class ChaterDetails {
	private UserEntity chater;

	private int id;

	private String email;

	private String name;

	public ChaterDetails(Date messageDate, UserEntity chater) {
		super();

		this.chater = chater;
		this.id = chater.getId();
		this.email = chater.getEmail();
		this.name = chater.getFirstName() + ", " + chater.getLastName();
	}

	public UserEntity getChater() {
		return chater;
	}

	public String getEmail() {
		return email;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setChater(UserEntity chater) {
		this.chater = chater;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
}
