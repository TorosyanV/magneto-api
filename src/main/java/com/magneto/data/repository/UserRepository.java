package com.magneto.data.repository;

import com.magneto.data.entity.UserEntity;
import com.magneto.data.entity.UserGeolocationEntity;
import com.magneto.location.Geolocation;

public interface UserRepository {

	UserEntity findById(int id);

	UserEntity findByEmail(String email);

    int createIfNotExist(UserEntity user);

	void refreshLocation(int userId, UserGeolocationEntity location);

	Geolocation getCurrentLocation(int userId);

}