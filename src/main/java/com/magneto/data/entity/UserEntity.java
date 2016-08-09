package com.magneto.data.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "USER", uniqueConstraints = { @UniqueConstraint(name = "uniqueEmail", columnNames = "EMAIL") })
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "SEX", nullable = false)
	private boolean sex;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "STATE", nullable = false)
	private String state = State.ACTIVE.getState();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USER_PROFILE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "PROFILE_ID") })
	private Set<Profile> userProfiles = new HashSet<Profile>();

	@OneToOne(optional = false, mappedBy = "user", cascade = CascadeType.ALL)
	private UserGeolocationEntity currentLocation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set<Profile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(Set<Profile> userProfiles) {
		this.userProfiles = userProfiles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", state=" + state + ", userProfiles=" + userProfiles + "]";
	}

	public UserGeolocationEntity getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(UserGeolocationEntity currentLocation) {
		this.currentLocation = currentLocation;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

}