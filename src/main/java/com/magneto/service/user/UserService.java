package com.magneto.service.user;

import java.util.List;

import com.magneto.data.entity.UserEntity;
import com.magneto.dto.NearUserInfoShortDto;
import com.magneto.dto.RegistrationResult;
import com.magneto.dto.UserDetailsDto;
import com.magneto.location.Geolocated;
import com.magneto.location.Geolocation;
import com.magneto.web.viewmodel.RegistrationRequest;

public interface UserService {

	UserEntity findById(int id);

	UserEntity findByEmail(String email);

	RegistrationResult createUserIfNotExist(RegistrationRequest registrationForm);

	void refreshLocation(Geolocated<Integer> located);

	Geolocation getCurrentLocation(com.magneto.dto.User user);

	List<NearUserInfoShortDto> getNerby(com.magneto.dto.User user,double meter);
	UserDetailsDto getUserDetail(int userId);
	
	

}