package com.magneto.data.repository;

import java.util.List;

import com.magneto.data.entity.UserGeolocationEntity;

public interface GeoRepository {

	List<UserGeolocationEntity> loadAllUsersLocation();
}
